<template>
  <div class="home">
    <div class="home-container">
      <div class="right">
        <img
          src="@/assets/images/illustration/SFW07_Full-2048x2048-removebg copy.png"
          alt=""
          class="chahua"
        />
      </div>
      <div class="left">
        <div class="welcome">
          <div class="welcome-text1">Welcome!</div>
          <div class="welcome-text2">子驿卿空间胶囊</div>
        </div>
        <!-- 原生登录表单 -->
        <form action="#" method="post" @submit.prevent="handleSubmit">
          <div class="input-group">
            <i
              class="icon iconfont icon-yonghuming"
              style="font-size: 20px; margin-right: 10px"
            ></i>
            <input
              type="text"
              id="username"
              name="username"
              v-model="form.username"
              placeholder="输入用户名"
              required
            />
          </div>
          <div class="input-group">
            <i
              class="icon iconfont icon-mimasuo"
              style="font-size: 20px; margin-right: 10px"
            ></i>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="输入密码"
              v-model="form.password"
              required
            />
          </div>
          <div class="button-group">
            <div class="sumbit"
            @click="handleSubmit"
            >登录</div>
            <div class="sumbit"
            @click="traverlogin"
            >游客访问</div>
          </div>
        </form>
        <div class="quote">
          <div>"人生如逆旅，你我亦行人" -- 谢谢访问！--</div>
        </div>
        <!-- <div class="gif">
          <img
            src="c:/Users/yaoyuhang/Downloads/icons8-car-2--unscreen.gif"
            alt=""
          />
        </div> -->
      </div>
    </div>
  </div>
</template>

 <script setup lang="js">
 import { reactive } from "vue";
 import { login } from "@/utils/request";
 import { useRouter } from 'vue-router';
 const router = useRouter();
 // #region 登录逻辑
 const form = reactive({
      username: '',
      password: '',
    });
    const handleSubmit = () => {
      login(form.username, form.password).then((res) => {
        console.log(res);
        if (res.data.code == 200) {
          router.push({ name: 'Homepage' });  
        } else {
          alert("登录失败");
        }
      });  
      // 提交表单
    };  
    const traverlogin = () => {
        router.push({ name: 'Homepage' });  
        // 提交表单
      };
 // #endregion
 </script>
<style scoped lang="scss">
@import "../common/common.scss";
.home {
  font-family: "大宝桃桃体", "Helvetica Neue", Helvetica, sans-serif;
  // #region 背景图片
  background-image: url("@/assets/images/illustration/海绵宝宝.jpg");
  /* 确保背景图片覆盖整个容器 */
  background-size: cover;
  /* 背景图片不重复 */
  background-repeat: no-repeat;
  /* 背景图片始终位于容器的中心 */
  background-position: center;
  /* 设置容器的高度，以确保背景图片可见 */
  height: 100vh; /* 或您需要的任何高度 */
  /* 其他样式 */
  // #endregion

  // #region flex布局
  display: flex;
  justify-content: center;
  align-items: center;
  // #endregion

  .home-container {
    // padding: 200px;
    height: 700px;
    width: 1460px;
    background-color: rgba(236, 232, 232, 0.514);
    border-radius: 20px;
    // #region flex布局
    display: flex;
    justify-content: space-between;
    align-items: center;
    // #endregion

    .right {
      width: 600px;
      height: 600px;
      margin-left: 100px;
      // 右边界虚线
      border-right: 3px dashed rgba(172, 167, 167, 0.568);
      // #region 插画
      img {
        height: 600px;
      }
      // #endregion
    }
    .left {
      width: 600px;
      height: 600px;
      // background-color: rgba(255, 255, 255, 0.514);
      margin-right: 100px;
      // #region flex布局
      display: flex;
      // justify-content: space-around;
      align-items: center;
      flex-direction: column;
      // #endregion
      .welcome {
        margin-top: 30px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .welcome-text1 {
          font-size: 50px;
          color: rgba(34, 117, 117, 0.767);
        }
        .welcome-text2 {
          font-size: 25px;
          color: rgba(58, 102, 102, 0.479);
        }
      }
      form {
        // margin-bottom: 40px;
        margin-top: 50px;
        display: flex;
        flex-direction: column;
        align-items: center;

        .input-group {
          position: relative;

          margin-bottom: 20px;
          input {
            width: 380px;
            height: 50px;
            border-radius: 25px;
            border: 2px solid rgba(12, 11, 11, 0.281);
            // padding-left: 10px;
            padding-left: 50px;
          }
          .iconfont {
            position: absolute;
            left: 20px; /* 或其他你需要的值，以确保图标与 input 框的左侧对齐 */
            top: 50%;
            transform: translateY(-50%); /* 垂直居中图标 */
            font-size: 16px; /* 或其他你需要的字体大小 */
            color: #999; /* 或其他你需要的颜色 */
          }
          .icon-yonghuming {
            font-size: 10px;
          }
        }
        .button-group {
          margin-top: 20px;
          width: 100%;
          display: flex;
          flex-direction: row;
          justify-content: space-between;

          .sumbit{
            text-align: center;
            line-height: 50px;
            font-size: 18px;
            // font-weight: bold;
            // 每个字之间有一点间隔
            letter-spacing: 3px;
            color: rgb(255, 255, 255);
            width: 150px;
            height: 50px;
            border-radius: 25px;
            border: 2px solid rgba(179, 173, 173, 0.685);
            background-color: rgba(67, 123, 226, 0.247);
          }
        }
      }
      .quote {
        margin-top: 25px;
        color: rgb(24, 139, 240);
      }
    }
  }
}
</style>
