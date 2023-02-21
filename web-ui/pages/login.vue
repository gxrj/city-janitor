<template>
    <div id="ghost"></div>
    <form>
        <label>Login:</label>
        <input name="login"/>
        <label>Senha:</label>
        <input name="password" type="password" />
        <button @click.prevent="logIn()">Logar</button>
    </form>
</template>

<script setup>
    definePageMeta( { layout: false } )
    function logIn() { navigateTo( { path: '/home' } ) }
</script>

<style>
    :root {
        height: 97vh;
        position: relative;
        background-color: rgb(48, 48, 48);
    }

    #ghost{ height: 25vh; }

    form {
        width: 50%;
        padding: 5%;
        margin: auto;
        display: grid;
        border-radius: 5px;
        border: 1px solid rgb(180, 180, 180);
        background-color: rgb(201, 201, 201);
    }

    input, button {
        margin-top: 5px;
        margin-bottom: 5px;
    }

    input:focus { outline: none; }

    button {
        color: white;
        height: 25px;
        cursor: pointer;
        border: 1px solid rgb(60, 60, 175);
        border-radius: 5px;
        background-color: rgb(70, 70, 175);
    }
</style>