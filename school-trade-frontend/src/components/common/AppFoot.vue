<template>
    <div class="foot-container">
        <div class="author">校园跳蚤市场</div>
    </div>
</template>

<script>
    export default {
        name: "Foot"
    }
</script>

<style scoped>
.foot-container {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px; /* Increased height to ensure better visibility */
    background-color: #f5f5f5;
    border-top: 1px solid #e0e0e0;
}

.author {
    color: #999999;
    font-size: 14px;
}
</style>
