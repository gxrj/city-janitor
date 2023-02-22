<template>
    <DataTable :headerLabels="headers" :data="data" />
</template>

<script setup>
    import DataTable from '~~/components/data-table.vue';

    let headers = [ 'Id', 'Nome' ]
    let data = [
        { id: 0, name: 'test' },
        { id: 1, name: 'test' },
        { id: 2, name: 'test' },
        { id: 3, name: 'test' },
        { id: 4, name: 'test' },
        { id: 5, name: 'test' }
    ]
</script>