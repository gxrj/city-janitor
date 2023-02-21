<template>
    <NuxtLink to="/login">Login</NuxtLink>
</template>
<script setup>
    //To do: check if user is logged in and if not redirect to login page
    navigateTo( { path: "/login" } )
</script>

<style>
    :root {
        height: 97vh;
    }
</style>