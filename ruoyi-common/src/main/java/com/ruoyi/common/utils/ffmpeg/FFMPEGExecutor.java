package com.ruoyi.common.utils.ffmpeg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A ffmpeg process wrapper.
 *
 * @author Carlo Pelliccia
 */
public class FFMPEGExecutor {

    /**
     * The path of the ffmpeg executable.
     */
    private String ffmpegExecutablePath;

    /**
     * Arguments for the executable.
     */
    private ArrayList args = new ArrayList();

    /**
     * The process representing the ffmpeg execution.
     */
    private Process ffmpeg = null;

    /**
     * A process killer to kill the ffmpeg process with a shutdown hook, useful
     * if the jvm execution is shutted down during an ongoing encoding process.
     */
    private ProcessKiller ffmpegKiller = null;

    /**
     * A stream reading from the ffmpeg process standard output channel.
     */
    private InputStream inputStream = null;

    /**
     * A stream writing in the ffmpeg process standard input channel.
     */
    private OutputStream outputStream = null;

    /**
     * A stream reading from the ffmpeg process standard error channel.
     */
    private InputStream errorStream = null;

    /**
     * It build the executor.
     *
     * @param ffmpegExecutablePath The path of the ffmpeg executable.
     */
    public FFMPEGExecutor(String ffmpegExecutablePath) {
        this.ffmpegExecutablePath = ffmpegExecutablePath;
    }

    /**
     * Adds an argument to the ffmpeg executable call.
     *
     * @param arg The argument.
     */
    public void addArgument(String arg) {
        args.add(arg);
    }

    /**
     * Executes the ffmpeg process with the previous given arguments.
     *
     * @throws IOException If the process call fails.
     */
    public void execute() throws IOException {
        int argsSize = args.size();
        String[] cmd = new String[argsSize + 1];
        cmd[0] = ffmpegExecutablePath;
        for (int i = 0; i < argsSize; i++) {
            cmd[i + 1] = (String) args.get(i);
        }
        Runtime runtime = Runtime.getRuntime();
        ffmpeg = runtime.exec(cmd);
        ffmpegKiller = new ProcessKiller(ffmpeg);
        runtime.addShutdownHook(ffmpegKiller);
        inputStream = ffmpeg.getInputStream();
        outputStream = ffmpeg.getOutputStream();
        errorStream = ffmpeg.getErrorStream();
    }


    public CmdResult execute2() throws IOException {
        CmdResult cmdResult = new CmdResult(false, "");
        List<String> cmds = new ArrayList<>();
        cmds.add(ffmpegExecutablePath);
        for (Object s : args) {
            cmds.add(s.toString());
        }
        ProcessBuilder builder = new ProcessBuilder(cmds);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            final StringBuilder stringBuilder = new StringBuilder();
            final InputStream inputStream = process.getInputStream();
            new Thread(new Runnable() {//启动新线程为异步读取缓冲器，防止线程阻塞
                @Override
                public void run() {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                            stringBuilder.append(line);
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }).start();
            process.waitFor();
            cmdResult.setSuccess(true);
            cmdResult.setMsg(stringBuilder.toString());
        } catch (Exception e) {
            throw new RuntimeException("ffmpeg执行异常" + e.getMessage());
        }
        return cmdResult;
    }

    /**
     * Returns a stream reading from the ffmpeg process standard output channel.
     *
     * @return A stream reading from the ffmpeg process standard output channel.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Returns a stream writing in the ffmpeg process standard input channel.
     *
     * @return A stream writing in the ffmpeg process standard input channel.
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Returns a stream reading from the ffmpeg process standard error channel.
     *
     * @return A stream reading from the ffmpeg process standard error channel.
     */
    public InputStream getErrorStream() {
        return errorStream;
    }

    /**
     * If there's a ffmpeg execution in progress, it kills it.
     */
    public void destroy() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable t) {
                ;
            }
            inputStream = null;
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable t) {
                ;
            }
            outputStream = null;
        }
        if (errorStream != null) {
            try {
                errorStream.close();
            } catch (Throwable t) {
                ;
            }
            errorStream = null;
        }
        if (ffmpeg != null) {
            ffmpeg.destroy();
            ffmpeg = null;
        }
        if (ffmpegKiller != null) {
            Runtime runtime = Runtime.getRuntime();
            runtime.removeShutdownHook(ffmpegKiller);
            ffmpegKiller = null;
        }
    }

}
