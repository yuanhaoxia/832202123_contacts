<template>
    <div class="page-container">
        <h1>欢迎使用{{$sysTitle}}</h1>
        <img src="@/assets/bg.jpg" />
    </div>
</template>

<script>
export default {
    name: 'Welcome'
};
</script>

<style lang="scss" scoped>
.page-container{
    text-align: center;
    img{
        width: 600px;
        border-radius: 8px;
    }
}
</style>