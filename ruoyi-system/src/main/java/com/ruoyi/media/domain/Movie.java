package com.ruoyi.media.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影对象 wm_movie
 *
 * @author thh
 * @date 2021-05-16
 */
@Data
@TableName(value = "wm_movie")
public class Movie extends BaseEntity
{

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long movieId;

    /** 封面 */
    @Excel(name = "封面")
    private String images;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 电影类型 */
    @Excel(name = "电影类型")
    private String type;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 标签 */
    @Excel(name = "标签")
    private String tagId;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 发布人 */
    @Excel(name = "发布人")
    private String publishBy;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 状态（0发布 1停用） */
    @Excel(name = "状态", readConverterExp = "0=发布,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long clickCount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long commentCount;

    /** 关注数 */
    @Excel(name = "关注数")
    private Long followCount;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long collectionCount;

    /** 支持数 */
    @Excel(name = "喜欢数")
    private Long supportCount;

    /** 反对数 */
    @Excel(name = "反对数")
    private Long opposeCount;

    /** 分享数 */
    @Excel(name = "分享数")
    private Long shareCount;

    /** 是否可以评论 */
    @Excel(name = "是否可以评论")
    private Integer openComment;

    /** 是否可以下载 */
    @Excel(name = "是否可以下载")
    private Integer openDownload;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 评分 */
    @Excel(name = "评分")
    private BigDecimal rate;

    /** 分类Id */
    @Excel(name = "分类Id")
    private Integer categoryId;

    /** 分类名称 */
    @Excel(name = "分类Id")
    private String categoryName;

    /** 专属二维码 */
    @Excel(name = "专属二维码")
    private String qrcodePath;

    /** 是否开启密钥模式 */
    private Integer openPassword;

    /** 私密访问时的密钥 */
    private String password;

    /** 查询标签数组 */
    @TableField(exist = false)
    private List<String> tagIdList;

    /** 查询发布年 */
    @TableField(exist = false)
    private String publishYear;

}
