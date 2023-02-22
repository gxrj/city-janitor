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
        min-height: 94.5vh;
    }
</style>