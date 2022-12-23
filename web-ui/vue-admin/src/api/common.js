import request from '@/utils/request'

// 上传图片
export function uploadImages(data) {
  return request({
    url: '/common/uploadImage',
    method: 'post',
    data: data
  })
}

