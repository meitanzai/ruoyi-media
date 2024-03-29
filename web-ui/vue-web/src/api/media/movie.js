import request from '@/utils/request'


export default {
// 查询电影管理列表
  listMovie(query) {
    return request({
      url: '/media/movie/list',
      method: 'get',
      params: query
    })
  },

  // 查询电影管理详细
  getMovie(movieId) {
    return request({
      url: '/media/movie/' + movieId,
      method: 'get'
    })
  },
  getMovieVideo(videoId) {
    return request({
      url: '/media/movie/video/' + videoId,
      method: 'get'
    })
  },

  // 查询演员相关电影
  getListByActorId(movieId) {
    return request({
      url: '/media/movie/getListByActorId/' + movieId,
      method: 'get'
    })
  },

  // 查询相关电影列表
  getSameTypeMovieList(movieId) {
    return request({
      url: '/media/movie/getSameTypeMovieList/' + movieId,
      method: 'get'
    })
  },
  checkPassword(param) {
    return request({
      url: '/media/movie/checkPassword',
      method: 'get',
      params: param
    })
  }
}



