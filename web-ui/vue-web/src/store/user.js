import { login, logout, getInfo } from '@/api/login';
import { getToken, setToken, removeToken } from '@/utils/auth';
import {getSignRecord, signIn} from "@/api/website/signRecord";

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    userId: '',
    permissions: [],
    loginFormVisible: false,  //  登录框显隐状态
    signRecord: {             //签到数据
      signinTodayFlag: 0,
      seriesDays: 0,
      continuityDays: 0
    },
    accountAmount: 0
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    },
    SET_USERID: (state, userId) => {
      state.userId = userId
    },
    SET_LOGINFORMVISIBLE: (state, loginFormVisible) => {
      state.loginFormVisible = loginFormVisible
    },
    SET_SIGNRECORD: (state, signRecord) =>{
      state.signRecord = signRecord
    },
    SET_ACCOUNTAMOUNT: (state, accountAmount) =>{
      state.accountAmount = accountAmount
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {
          setToken(res.token)
          commit('SET_TOKEN', res.token)
          commit('SET_LOGINFORMVISIBLE', false);
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //签到
    SignIn({ commit }){
      signIn().then(response => {
        commit('SET_SIGNRECORD', response.data);
        commit('SET_ACCOUNTAMOUNT', response.data.accountAmount);
      })
    },
    // 获取签到数据
    GetSignData({ commit }){
      getSignRecord().then(response => {
        commit('SET_SIGNRECORD', response.data);
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo().then(res => {
          const user = res.user
          const avatar = user.avatar == "" ? require("@/assets/styles/images/user.png") : process.env.VUE_APP_FILE_UPLOAD_HOST + user.avatar;
          if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', res.roles)
            commit('SET_PERMISSIONS', res.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_NAME', user.userName);
          commit('SET_AVATAR', avatar);
          commit('SET_USERID', user.userId);
          commit('SET_ACCOUNTAMOUNT', res.accountAmount)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    //跳转到登录页面
    showLoginForm({commit}){
      return new Promise(resolve => {
        commit('SET_LOGINFORMVISIBLE', true);
        resolve();
        //window.location.href = `/login?redirect=${window.location.href}`
      });

    },
    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken();
          commit('SET_SIGNRECORD', {
            signinTodayFlag: 0,
            seriesDays: 0,
            continuityDays: 0
          });
          commit('SET_ACCOUNTAMOUNT', 0)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken();
        commit('SET_SIGNRECORD', {
          signinTodayFlag: 0,
          seriesDays: 0,
          continuityDays: 0
        });
        resolve()
      })
    }
  }
}

export default user
