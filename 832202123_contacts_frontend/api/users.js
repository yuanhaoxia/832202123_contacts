import request from '@/utils/request'

//根据条件分页查询
export const findUsersPageAPI = (pageNum,pageSize,params) => {
    return request({
        url: `/users/search/${pageNum}/${pageSize}`,
        method: 'get',
        params
    })
}


//新增
export const addUsersAPI = data => {
    return request({
        url: '/users/',
        method: 'post',
        data
    })
}

//修改
export const modifyUsersAPI = data => {
    return request({
        url: '/users/',
        method: 'put',
        data
    })
}

//删除
export const removeUsersAPI = id => {
    return request({
        url: `/users/${id}`,
        method: 'delete'
    })
}
