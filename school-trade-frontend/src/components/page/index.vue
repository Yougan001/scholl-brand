<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="content">
                <el-tabs v-model="labelName" type="card" @tab-click="handleClick" class="custom-tabs">
                    <el-tab-pane label="全部" name="0"></el-tab-pane>
                    <el-tab-pane label="数码" name="1"></el-tab-pane>
                    <el-tab-pane label="家电" name="2"></el-tab-pane>
                    <el-tab-pane label="户外" name="3"></el-tab-pane>
                    <el-tab-pane label="图书" name="4"></el-tab-pane>
                    <el-tab-pane label="其他" name="5"></el-tab-pane>
                </el-tabs>

                <div class="idle-list">
                    <el-row :gutter="30">
                        <el-col :span="6" v-for="(idle, index) in idleList" :key="index">
                            <div class="idle-card" @click="toDetails(idle)">
                                <el-image
                                    class="idle-image"
                                    :src="idle.imgUrl"
                                    fit="cover">
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline">无图</i>
                                    </div>
                                </el-image>
                                <div class="idle-content">
                                    <div class="idle-title">{{ idle.idleName }}</div>
                                    <el-row class="idle-details">
                                        <el-col :span="12">
                                            <div class="idle-price">￥{{ idle.idlePrice }}</div>
                                        </el-col>
                                        <el-col :span="12">
                                            <div class="idle-place">{{ idle.idlePlace }}</div>
                                        </el-col>
                                    </el-row>
                                    <div class="user-info">
                                        <el-image
                                            class="user-avatar"
                                            :src="idle.user.avatar"
                                            fit="cover">
                                            <div slot="error" class="image-slot">
                                                <i class="el-icon-picture-outline">无图</i>
                                            </div>
                                        </el-image>
                                        <div class="user-nickname">{{ idle.user.nickname }}</div>
                                    </div>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
                <div class="pagination">
                    <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-size="8"
                        layout="prev, pager, next, jumper"
                        :total="totalItem">
                    </el-pagination>
                </div>
            </div>
            <app-foot></app-foot>
        </app-body>
    </div>
</template>

<script>
    import AppHead from '../common/AppHeader.vue';
    import AppBody from '../common/AppPageBody.vue';
    import AppFoot from '../common/AppFoot.vue';

    export default {
        name: "index",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                labelName: '0',
                idleList: [],
                currentPage: 1,
                totalItem: 1
            };
        },
        created() {
            this.findIdleTiem(1);
        },
        watch: {
            $route(to, from) {
                this.labelName = to.query.labelName;
                let val = parseInt(to.query.page) ? parseInt(to.query.page) : 1;
                this.currentPage = parseInt(to.query.page) ? parseInt(to.query.page) : 1;
                this.findIdleTiem(val);
            }
        },
        methods: {
            findIdleTiem(page) {
                const loading = this.$loading({
                    lock: true,
                    text: '加载数据中',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.1)'
                });
                if (this.labelName > 0) {
                    this.$api.findIdleTiemByLable({
                        idleLabel: this.labelName,
                        page: page,
                        nums: 8
                    }).then(res => {
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            list[i].timeStr = list[i].releaseTime.substring(0, 10) + " " + list[i].releaseTime.substring(11, 19);
                            let pictureList = JSON.parse(list[i].pictureList);
                            list[i].imgUrl = pictureList.length > 0 ? pictureList[0] : '';
                        }
                        this.idleList = list;
                        this.totalItem = res.data.count;
                    }).catch(e => {
                        console.log(e);
                    }).finally(() => {
                        loading.close();
                    });
                } else {
                    this.$api.findIdleTiem({
                        page: page,
                        nums: 8
                    }).then(res => {
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            list[i].timeStr = list[i].releaseTime.substring(0, 10) + " " + list[i].releaseTime.substring(11, 19);
                            let pictureList = JSON.parse(list[i].pictureList);
                            list[i].imgUrl = pictureList.length > 0 ? pictureList[0] : '';
                        }
                        this.idleList = list;
                        this.totalItem = res.data.count;
                    }).catch(e => {
                        console.log(e);
                    }).finally(() => {
                        loading.close();
                    });
                }
            },
            handleClick(tab, event) {
                this.$router.replace({ query: { page: 1, labelName: this.labelName } });
            },
            handleCurrentChange(val) {
                this.$router.replace({ query: { page: val, labelName: this.labelName } });
            },
            toDetails(idle) {
                this.$router.push({ path: '/details', query: { id: idle.id } });
            }
        }
    };
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

.content {
    min-height: 85vh;
    padding: 20px;
    background-color: #f5f5f5;
    font-family: 'Roboto', sans-serif;
}

.custom-tabs {
    margin-bottom: 20px;
}

.custom-tabs .el-tabs__item {
    color: #1e88e5;
    transition: color 0.3s;
}

.custom-tabs .el-tabs__item:hover {
    color: #0d47a1;
}

.custom-tabs .el-tabs__item.is-active {
    color: #0d47a1;
}

.idle-list {
    margin: 0 20px;
}

.idle-card {
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    overflow: hidden;
    cursor: pointer;
    transition: box-shadow 0.3s, transform 0.3s;
    background-color: #ffffff;
}

.idle-card:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    transform: translateY(-5px);
}

.idle-image {
    width: 100%;
    height: 160px;
    object-fit: cover;
}

.idle-content {
    padding: 10px;
}

.idle-title {
    font-size: 16px;
    font-weight: 500;
    margin-bottom: 10px;
    color: #333;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}

.idle-details {
    margin-bottom: 10px;
}

.idle-price {
    font-size: 16px;
    color: #0d47a1;
}

.idle-place {
    font-size: 14px;
    color: #666;
    text-align: right;
}

.user-info {
    display: flex;
    align-items: center;
}

.user-avatar {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    margin-right: 10px;
}

.user-nickname {
    font-size: 14px;
    color: #999;
}

.pagination {
    display: flex;
    justify-content: center;
    padding: 20px 0;
}
</style>
