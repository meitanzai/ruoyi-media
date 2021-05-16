package com.ruoyi.media.mapper;

import java.util.List;
import com.ruoyi.media.domain.MovieActor;

/**
 * 电影演员关联Mapper接口
 * 
 * @author thh
 * @date 2021-05-16
 */
public interface MovieActorMapper 
{
    /**
     * 查询电影演员关联
     * 
     * @param actorId 电影演员关联ID
     * @return 电影演员关联
     */
    public MovieActor selectMovieActorById(Long actorId);

    /**
     * 查询电影演员关联列表
     * 
     * @param movieActor 电影演员关联
     * @return 电影演员关联集合
     */
    public List<MovieActor> selectMovieActorList(MovieActor movieActor);

    /**
     * 新增电影演员关联
     * 
     * @param movieActor 电影演员关联
     * @return 结果
     */
    public int insertMovieActor(MovieActor movieActor);

    /**
     * 修改电影演员关联
     * 
     * @param movieActor 电影演员关联
     * @return 结果
     */
    public int updateMovieActor(MovieActor movieActor);

    /**
     * 删除电影演员关联
     * 
     * @param actorId 电影演员关联ID
     * @return 结果
     */
    public int deleteMovieActorById(Long actorId);

    /**
     * 批量删除电影演员关联
     * 
     * @param actorIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMovieActorByIds(Long[] actorIds);
}
