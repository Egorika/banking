new Vue({
    el: '#test',
    data: {
        categories: [],
        table: []
    },
    mounted: function () {
        axios.get('/product/all')
            .then(response => {
                this.categories = response.data;
            });
    },
    computed: {
        credits: function () {
            return this.categories.filter(product => product.type === 'CREDIT')
        },
        debits: function () {
            return this.categories.filter(product => product.type === 'DEBIT')
        },
        tableExists: function () {
            return this.table != null
        }
    },
    methods: {
        getAllByType: function (data, type) {
            return this.data.filter(product => product.type === type)
        },

        creditTable: function () {
            this.table = this.credits;
        },
        debitTable: function () {
            this.table = this.debits;
        }
    }
});
