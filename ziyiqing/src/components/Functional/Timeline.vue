<template>
  <div class="timeline">
    <div class="box">
      <ul>
        <li>
          <div class="item">
            <span> 大一学年 </span>
            <achievement />
          </div>
        </li>
        <li>
          <div class="item">
            <span> 大二学年 </span>
            <achievement />
          </div>
        </li>
      </ul>
    </div>
    <div class="box">
      <ul>
        <li>
          <div class="item">
            <span> 大三学年 </span>
            <achievement />
          </div>
        </li>
        <li>
          <div class="item">
            <span> 大四学年 </span>
            <achievement />
          </div>
        </li>
      </ul>
      
    </div>
  </div>
</template>

 <script setup lang="js">
import {computed} from 'vue';
import achievement from '@/components/Functional/Achievement.vue';
components:{
  achievement
}

 </script>
<style scoped lang="scss">
@import '@/SCSS/components/Functional/Timeline.scss'
</style>

