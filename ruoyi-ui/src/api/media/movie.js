import request from '@/utils/request'

// 查询电影管理列表
export function listMovie(query) {
  return request({
    url: '/media/movie/list',
    method: 'get',
    params: query
  })
}

// 查询电影管理详细
export function getMovie(movieId) {
  return request({
    url: '/media/movie/' + movieId,
    method: 'get'
  })
}

// 新增电影管理
export function addMovie(data) {
  return request({
    url: '/media/movie',
    method: 'post',
    data: data
  })
}

// 修改电影管理
export function updateMovie(data) {
  return request({
    url: '/media/movie',
    method: 'put',
    data: data
  })
}

// 删除电影管理
export function delMovie(movieId) {
  return request({
    url: '/media/movie/' + movieId,
    method: 'delete'
  })
}

// 导出电影管理
export function exportMovie(query) {
  return request({
    url: '/media/movie/export',
    method: 'get',
    params: query
  })
}



// 上传电影封面
export function uploadMovieImages(data) {
  return request({
    url: '/media/movie/upload/images',
    method: 'post',
    data: data
  })
}

// 上传电影视频
  export function uploadMovieVideo(data) {
    return request({
      url: '/media/movie/upload/video',
      method: 'post',
      data: data
    })
}
