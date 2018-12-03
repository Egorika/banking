new Vue({
    el: '#today',
    data: {
        now: null
    },
    mounted: function () {
        let day = new Date().getDate();
        let month = new Date().getMonth() + 1;
        let year = new Date().getFullYear();

        this.now = day + '.' + month + '.' + year;
    },
});


new Vue({
    el: '#exchange',
    data: {
        usd: null,
        eur: null,
        rub: null
    },
    mounted: function () {
        axios.get('http://www.nbrb.by/API/ExRates/Rates/RUB?ParamMode=2')
            .then(response => {
                this.rub = response.data;
            });
        axios.get('http://www.nbrb.by/API/ExRates/Rates/EUR?ParamMode=2')
            .then(response => {
                this.eur = response.data;
            });
        axios.get('http://www.nbrb.by/API/ExRates/Rates/USD?ParamMode=2')
            .then(response => {
                this.usd = response.data;
            });
    },
});
