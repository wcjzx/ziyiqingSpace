<template>
  <div class="project">
    <div class="context">
      <div style="float: left; border-right: 2px solid #020c1d">
        <h1 style="margin-right: 20px">项<br />目</h1>
      </div>
      <div class="right">
        <div>
          <div class="fourp">
            <div><img src="" alt="tupian" /></div>
            <div></div>
          </div>
          <div class="fourp" style="margin-right:0;">
            <div><img src="" alt="tupian" /></div>
            <div></div>
          </div>
          <div class="fourp" >
            <div><img src="" alt="tupian" /></div>
            
            <div></div>
          </div>
          <div class="fourp" style="margin-right:0;">
            <div><img src="" alt="tupian" /></div>
            <div></div>
          </div>
        </div>
      </div>
      <div class="r-right">
            <router-link class="link" active-class="active" to="/resume">更多</router-link>
        </div> 
    </div>
    <end/>
  </div>
 
</template>

 <script setup lang="js">
import {computed} from 'vue'
import  slideDownward from '@/components/Resume/SlideDownward.vue'
import  end from '@/components/Resume/End.vue'
components:{
    slideDownward,
    end
}

 </script>
<style scoped lang="scss">
.project {
  .context {
    height: 470px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    div {
        // margin-right: 40px;
      h1 {
        line-height: 150px;
      }
    }
    .right {
        width:500px;
        margin-left: 80px;
      div {
        display: flex;
        flex-wrap: wrap;
        // background: #207eb8;
        .fourp {
          width: 200px;
          height: 200px;
          margin: 10px;
          border-radius: 20px;
          margin-right: 50px;
          div {
            img{
                width: 180px;
                height: 90px;
                background: #4a1c4a;
                border-radius: 20px;
            }
          }
          div {
            background: #729f43;
            border-radius: 20px;
          }
        }
      }
    
    }
    .r-right{
margin: 0px;
padding: 0px;
font-size: 20px;

}
  }
}
</style>
