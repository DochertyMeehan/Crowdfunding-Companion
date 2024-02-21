<template>
    <div class="search-box-container">
        <input id="search-box" type="text" v-model="searchQuery" @input="updateSearchQuery"/>
        <label for="search-box">Filter</label>
    </div>
</template>

<script>

export default {
    name: 'search-box',
    data() {
        return {
            searchQuery: '',
        }
    },
    methods: {
        updateSearchQuery() {
            // update searchTerm in store so other components can use it
            // this.searchTerm = this.searchTerm.toUpperCase();

            /* DO NOT DO THIS!!!! */
            // this.$store.state.searchTerm = this.searchTerm;
            this.$emit('updateSearchQuery',  this.searchQuery);
        }
    }
}
</script>


<style scoped>
div.search-box-container>label {
    padding-left: 10px;
    font-weight: bold;
}
</style>