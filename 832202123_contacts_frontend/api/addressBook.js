import request from '@/utils/request'



//根据条件分页查询
export const findAddressBookPageAPI = (pageNum,pageSize,params) => {
    return request({
        url: `/addressBook/search/${pageNum}/${pageSize}`,
        method: 'get',
        params
    })
}



//根据id查询
export const findAddressBookByIdAPI = (id) => {
    return request({
        url: '/addressBook/'+id,
        method: 'get'
    })
}

//新增
export const addAddressBookAPI = data => {
    return request({
        url: '/addressBook/',
        method: 'post',
        data
    })
}

//修改
export const modifyAddressBookAPI = data => {
    return request({
        url: '/addressBook/',
        method: 'put',
        data
    })
}

//删除
export const removeAddressBookAPI = id => {
    return request({
        url: `/addressBook/${id}`,
        method: 'delete'
    })
}
