<template>
    <table>
        <thead>
            <tr id="table-top">
                <td colspan="100">{{ tableName }}</td>
            </tr>
            <tr>
                <td v-for="label in headerLabels">{{ label }}</td>
            </tr>
        </thead>
        <tbody>
            <tr v-for="element in data">
                <td v-for="key of Object.keys( element )">{{ element[ key ] }}</td>
            </tr>
        </tbody>
        <tr id="table-bottom">
            <td colspan="100">Paginator to be done</td>
        </tr>
    </table>
</template>

<script>
    export default {
        props: {
            tableName: { type: String, default: 'Data Table' },
            headerLabels: { type: Array, required: true },
            data: { type: Array, required: true }
        }
    }
</script>

<style>
    table{
        width: 100%;
        height: auto;
        margin: auto;
        border-collapse: collapse;
    }
    #table-top, thead > tr, #table-bottom {
        color: white;
        background-color: rgb(70, 70, 175);
    }
    tbody {
        background-color: rgb(197, 197, 197);
    }
    tbody > tr:hover {
        color: white;
        cursor: pointer;
        background-color: rgb(78, 78, 78);
    }
    td {
        text-align: center;
    }
    #table-top > td {
        border-radius: 5px 5px 0px 0px;
    }
    #table-bottom > td {
        border-radius: 0px 0px 5px 5px;
    }
</style>