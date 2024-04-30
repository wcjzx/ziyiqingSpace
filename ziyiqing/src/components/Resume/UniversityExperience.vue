<template>
  <div class="universityExperience">
    <div class="text">
      <div style="float: left;  border-right: 2px solid #020c1d; ">
        <h1 style="margin-right:20px;">大<br>学<br>生<br>活</h1>
      </div>
      <div class="right">
        <div class="tupian">
          <img src="@/assets/images/image.png" alt="" />
        </div>
        <timeline />
      </div>
    </div>
    <slideDownward />
  </div>
</template>

 <script setup lang="js">
import {computed} from 'vue';
import slideDownward from '@/components/Resume/SlideDownward.vue';
import timeline from '@/components/Functional/Timeline.vue';
components:{
    slideDownward,
    timeline
}
 </script>
<style scoped lang="scss">
@import '@/SCSS/components/Resume/UniversityExperience.scss'
</style>