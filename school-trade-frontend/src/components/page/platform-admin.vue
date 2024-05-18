<template>
    <div class="container">
        <el-container>
            <el-header>
                <div class="header">
                    <div class="app-name">
                        <router-link to="/platform-admin">
                            <span class="app-name-text">后台管理</span>
                        </router-link>
                    </div>
                    <span class="app-title">{{ admin.nickname }}</span>
                    <div class="app-logOut">
                        <el-button class="logout-button" type="primary" @click="logout">退出登录</el-button>
                    </div>
                </div>
            </el-header>
            <el-container>
                <div class="mainBody">
                    <el-aside class="aside-menu">
                        <el-col :span="24">
                            <el-menu default-active="1" class="el-menu-vertical-demo" @select="handleSelect"
                                background-color="#2c3e50" text-color="#fff" active-text-color="#ffd04b">
                                <el-menu-item index="1">
                                    <i class="el-icon-goods"></i>
                                    <span>商品管理</span>
                                </el-menu-item>
                                <el-menu-item index="2">
                                    <i class="el-icon-s-goods"></i>
                                    <span>订单管理</span>
                                </el-menu-item>
                                <el-menu-item index="3">
                                    <i class="el-icon-s-custom"></i>
                                    <span>用户管理</span>
                                </el-menu-item>
                            </el-menu>
                        </el-col>
                    </el-aside>
                    <el-main class="main-content">
                        <transition name="fade" mode="out-in">
                            <component :is="currentComponent"></component>
                        </transition>
                    </el-main>
                </div>
            </el-container>
        </el-container>
        <div class="foot">
            <app-foot></app-foot>
        </div>
    </div>
</template>

<script>
import AppFoot from '../common/AppFoot.vue'
import IdleGoods from '../common/IdleGoods.vue'
import orderList from '../common/orderList.vue'
import userList from '../common/userList.vue'

export default {
    name: "platform-admin",
    components: {
        AppFoot,
        IdleGoods,
        orderList,
        userList,
    },
    data() {
        return {
            mode: 1,
            admin: {
                nickname: '管理员',
            },
        }
    },
    created() {
        this.admin.nickname = this.$sta.adminName;
    },
    methods: {
        logout() {
            this.$api.loginOut({}).then(res => {
                if (res.status_code === 1) {
                    this.$sta.isLogin = false;
                    this.$sta.adminName = '';
                    this.$router.push({ path: '/login-admin' });
                }
            }).catch(e => {
                console.log(e)
            })
        },
        handleSelect(val) {
            this.mode = val;
        },
    },
    computed: {
        currentComponent() {
            switch (this.mode) {
                case '1':
                    return IdleGoods;
                case '2':
                    return orderList;
                case '3':
                    return userList;
                default:
                    return IdleGoods;
            }
        },
    },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

.container {
    background-color: #f6f6f6;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    font-family: 'Roboto', sans-serif;
}

.header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 60px;
    background: #2c3e50;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    z-index: 1000;
}

.app-name {
    display: flex;
    align-items: center;
}

.app-name-text {
    color: #ffd04b;
    font-size: 20px;
    font-weight: 700;
    text-transform: uppercase;
}

.app-title {
    color: #ffffff;
    font-size: 16px;
    font-weight: 500;
}

.app-logOut {
    display: flex;
    align-items: center;
}

.logout-button {
    margin-right: 20px;
}

.mainBody {
    display: flex;
    width: 100%;
}

.aside-menu {
    width: 240px;
    min-height: calc(100vh - 60px);
    background-color: #2c3e50;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.main-content {
    flex: 1;
    padding: 20px;
    background-color: #ffffff;
    min-height: calc(100vh - 60px);
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    overflow-y: auto;
}

.foot {
    width: 100%;
    height: 60px;
    background-color: #2c3e50;
    color: #ffffff;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: auto;
    box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.1);
}

.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s;
}

.fade-enter-active, .fade-leave-active {
    transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
    opacity: 0;
}
</style>
