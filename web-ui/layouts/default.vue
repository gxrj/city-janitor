<template>
    <Header />
    <div id="content">
        <slot />
    </div>
    <Footer/>
</template>

<style>
    *{
        margin: 0;
        width: auto;
    }
    #content {
        margin-top: 2%;
        min-height: 90vh;
    }
</style>