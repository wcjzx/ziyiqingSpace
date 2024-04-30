<template>
    <div class="DropDownBox">
        <t-space>
            <t-dropdown :options="options" trigger="click" @click="clickHandler">
                <t-space>
                    <t-button variant="text">
                        更多
                        <template #suffix> <t-icon name="chevron-down" size="16" /></template>
                    </t-button>
                </t-space>
            </t-dropdown>
        </t-space>
    </div>

</template>
<script setup>
import { MessagePlugin } from 'tdesign-vue-next';
import { useRouter } from 'vue-router';
import { deleteArticleById } from '@/utils/request';
const options = [
    { content: '编辑', value: 1 },
    { content: '删除', value: 2 },
];
const props = defineProps({
    ArticleId: {
        type: Number, // Assuming ArticleId is a number, adjust as needed  
        required: true
    }
});

const router = useRouter();
const clickHandler = (data) => {
    // 在这个作用域内，你可以直接访问到 props.ArticleId  
    if (data.value === 1) {
        MessagePlugin.success(`进入【${data.content}】`);
        router.push({
            name: 'EditArticle',
            query: { ArticleId: props.ArticleId },
        }) 

    } if (data.value === 2) {
        MessagePlugin.success(`进入【${data.content}】`);
        deleteArticleById(props.ArticleId).then(() => {  
        // 在删除文章成功后刷新页面  
        location.reload();  
    }).catch((error) => {  
        // 处理删除文章失败的情况  
        console.error('删除文章失败:', error);  
    });  
    }
}; 
</script>