var inicio = new Vue({
  el: "#app",
  data: {
    nome: "",
    listaSetores: []
  },
  created: function () {
    this.listarSetores();
  },
  methods: {
    //Busca os itens para a lista da primeira página
    criarSetor: function () {
      const vm = this;
      axios.post("/funcionarios/rest/setores", {
        nome: vm.$data.nome,
      })
        .then(response => {
          window.alert(`Setor Criado`)
          vm.limparFormulario();
          vm.listarSetores();
        })
    },
    limparFormulario() {
      const vm = this;
      vm.$data.nome = "";
    },
    mostraAlertaErro: function (erro, mensagem) {
      console.log(erro);
      alert(mensagem);
    },
    listarSetores() {
      const vm = this;
      axios.get('/funcionarios/rest/setores')
          .then((response) => {
            vm.listaSetores = response.data;
          }).catch((error) => {
        vm.mostraAlertaErro("Erro interno", "Não foi possível lista setores")
      })
    },
  }
});