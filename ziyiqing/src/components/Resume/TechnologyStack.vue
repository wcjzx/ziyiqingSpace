<template>
  <div class="technologyStack">
    <div class="context">
      <div class="top">
        <h1>技术栈</h1>
      </div>
      <div class="botton">
        <div class="double">
          <div class="b-top">
            <h2>前端</h2>
            <b><-----------------------></b>
          </div>
          <div class="b-botton">
            <ul>
              <li>lalalal</li>
              <li>lalalal</li>
              <li>lalalal</li>
            </ul>
          </div>
        </div>
        <div class="double">
          <div class="b-top">
            <h2>后端</h2>
          </div>
          <b><-----------------------></b>
          <div class="b-botton">
            <ul>
              <li>lalalal</li>
              <li>lalalal</li>
              <li>lalalal</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <slideDownward />
  </div>
</template>

 <script setup lang="js">
import {computed} from 'vue';
import slideDownward from '@/components/Resume/SlideDownward.vue';
components:{
    slideDownward
}

 </script>
<style scoped lang="scss">
@import '@/SCSS/components/Resume/TechnologyStack.scss'
</style>


