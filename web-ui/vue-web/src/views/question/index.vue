<template>
  <i-frame :src="url"/>
</template>
<script>
import iFrame from "@/components/iFrame/index";
import {getConfigKey} from "@/api/system/config";

export default {
  name: "Question",
  components: {iFrame},
  data() {
    return {
      url: 'https://chat.forchange.cn'
    };
  },
  created() {
    this.getReadMeUrl()
  },
  methods: {
    getReadMeUrl() {
      getConfigKey("questionUrl").then(res => {
        this.url = res.msg
      });
    }
  }
};
</script>
