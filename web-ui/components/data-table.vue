<template>
    <table>
        <tbody>
            <tr>
                <td v-for="label in headerLabels">{{ label }}</td>
            </tr>
            <tr v-for="element in data">
                <td v-for="key of Object.keys( element )">{{ element[ key ] }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script>
    export default {
        props: {
            headerLabels: { type: Array, required: true },
            data: { type: Array, required: true }
        }
    }
</script>