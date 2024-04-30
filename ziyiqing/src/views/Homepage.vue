<template>
  <div class="Homepage">
    <div class="middleLevel">
      <personalResumeCard :User="User"></personalResumeCard>
      <div class="project">
        <famousQuoteCard />
        <listOfItems />
      </div>
    </div>
  </div>
</template>
<script setup lang="js">
import { ref,reactive,onBeforeMount } from "vue"
import famousQuoteCard from "@/components/Homepage/FamousQuoteCard.vue"
import personalResumeCard from "@/components/Homepage/PersonalResumeCard.vue"
import listOfItems from "@/components/Homepage/ListOfItems.vue"


</script>

<style scoped lang="scss">
// @import '@/SCSS/views/Homepage.scss'

@import "@/common/common";

.Homepage {
  margin: 0 100px;
  .middleLevel {
    display: flex;
    justify-content: center;
    margin-top: 35px;
  }
  .personalResumeCard {

  }
  .project {
    height: 400px;
    margin-left:40px ;
  }
  .listOfItems {
  }
}
</style>
