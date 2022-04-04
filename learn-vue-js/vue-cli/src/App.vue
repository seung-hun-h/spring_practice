<template>
  <AppHeader v-bind:propsdata="str" v-on:renew="renewStr"/>
</template>

<script>
import AppHeader from './components/AppHeader.vue'

export default {
  data: function() {
    return {
      str: 'Header'
    }
  },
  methods: {
    renewStr: function(newValue) {
      this.str = newValue;
    }
  },
  components: {
    AppHeader,
  }
}
</script>

<style>

</style>