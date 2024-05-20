<template>
    <div class="header">
        <div class="header-container">
            <router-link to="/" class="app-name">
                <img src="../../assets/logo.png" class="logo">
                <b class="app-title">校园跳蚤市场</b>
            </router-link>
            <div class="search-container">
                <el-input placeholder="请输入关键词..." v-model="searchValue" @keyup.enter.native="searchIdle" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="searchIdle" class="search-button">搜索</el-button>
                </el-input>
            </div>
            <div class="actions">
                <el-button type="primary" icon="el-icon-plus" @click="toRelease" class="action-button">物品发布</el-button>
                <el-button type="primary" icon="el-icon-chat-dot-round" @click="toMessage" class="action-button">消息</el-button>
                <router-link v-if="!isLogin" class="login-link" to="/login">登录</router-link>
                <el-dropdown trigger="click" v-else class="user-dropdown">
                    <div class="user-info">
                        <span class="nickname">{{ nicknameValue || nickname }}</span>
                        <el-avatar :src="avatarValue || avatar" class="avatar"></el-avatar>
                    </div>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click="toMe">个人中心</el-dropdown-item>
                        <el-dropdown-item divided class="logout" @click="loginOut">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Header',
    props: ['searchInput', 'nicknameValue', 'avatarValue'],
    data() {
        return {
            searchValue: this.searchInput,
            nickname: '登录',
            avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
            isLogin: false
        };
    },
    created() {
        if (!this.$globalData.userInfo.nickname) {
            this.$api.getUserInfo().then(res => {
                if (res.status_code === 1) {
                    this.nickname = res.data.nickname;
                    this.avatar = res.data.avatar;
                    res.data.signInTime = res.data.signInTime.substring(0, 10);
                    this.$globalData.userInfo = res.data;
                    this.isLogin = true;
                }
            });
        } else {
            this.nickname = this.$globalData.userInfo.nickname;
            this.avatar = this.$globalData.userInfo.avatar;
            this.isLogin = true;
        }
    },
    methods: {
        searchIdle() {
            if ('/search' !== this.$route.path) {
                this.$router.push({ path: '/search', query: { searchValue: this.searchValue } });
            } else {
                this.$router.replace({ path: '/search', query: { searchValue: this.searchValue } });
                this.$router.go(0);
            }
        },
        toMe() {
            if ('/me' !== this.$route.path) {
                this.$router.push({ path: '/me' });
            }
        },
        toMessage() {
            if ('/message' !== this.$route.path) {
                this.$router.push({ path: '/message' });
            }
        },
        toRelease() {
            if ('/release' !== this.$route.path) {
                this.$router.push({ path: '/release' });
            }
        },
        loginOut() {
            this.$api.logout().then(res => {
                if (res.status_code === 1) {
                    this.$globalData.userInfo = {};
                    if ('/index' === this.$route.path) {
                        this.$router.go(0);
                    } else {
                        this.$router.push({ path: '/index' });
                    }
                } else {
                    this.$message.error('网络或系统异常，退出登录失败！');
                }
            });
        }
    }
};
</script>

<style scoped>
.header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 64px;
    background: #ffffff;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #eeeeee;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    z-index: 1000;
}

.header-container {
    width: 1200px;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.app-name {
    display: flex;
    align-items: center;
    text-decoration: none;
}

.logo {
    width: 40px;
    margin-right: 10px;
}

.app-title {
    color: #409EFF;
    font-size: 24px;
    font-weight: bold;
}

.search-container {
    flex-grow: 1;
    margin: 0 20px;
}

.search-button {
    background-color: #409EFF;
    color: white;
}

.actions {
    display: flex;
    align-items: center;
    gap: 15px;
}

.action-button {
    background-color: #409EFF;
    color: white;
}

.login-link {
    font-size: 16px;
    font-weight: 600;
    color: #409EFF;
    text-decoration: none;
}

.user-dropdown {
    display: flex;
    align-items: center;
}

.user-info {
    display: flex;
    align-items: center;
    cursor: pointer;
}

.nickname {
    font-size: 16px;
    color: #409EFF;
    margin-right: 10px;
}

.avatar {
    width: 32px;
    height: 32px;
}

.logout {
    color: red;
}
</style>
