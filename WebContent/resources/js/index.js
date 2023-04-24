var inicio = new Vue({
	el: "#inicio",
	data: {
		lista: [],
		nome: "",
		setor: null,
		idade: null,
		email: "",
		salario: null,
		listaSetores: [],
		editandoFuncionario: false,
		idFuncionario: null
	},
	created: function () {
		let vm = this;
		vm.listarFuncionarios();
		vm.buscarSetores()
	},
	methods: {
		//Busca os itens para a lista da primeira página
		listarFuncionarios: function () {
			const vm = this;

			axios.get("/funcionarios/rest/funcionarios")
				.then(response => {
					vm.lista = response.data;
					console.log(vm.lista)
				}).catch(function (error) {
					vm.mostraAlertaErro("Erro interno", "Não foi possível listar natureza de serviços");
				}).finally(function () {
				});
		},
		deletarFuncionario: function (id) {
			console.log(id)
			axios.delete(`/funcionarios/rest/funcionarios/${id}`)
				.then(response => {
					window.alert(`Usuário ${id} Excluído`)
					location.reload()
				})

		},
		editarFuncionario: function () {
			const vm = this
			axios.put(`/funcionarios/rest/funcionarios/${vm.$data.idFuncionario}`, {
				nome: vm.$data.nome,
				setor: { id: Number(vm.$data.setor) },
				idade: Number(vm.$data.idade),
				salario: Number(vm.$data.salario),
				email: vm.$data.email,
			})
				.then((response) => {
					alert('Funcionario editado com sucesso')
					location.reload()
				}).catch((erro) => {
					vm.mostrarErro("Erro interno", "Não foi possível editar funcionário")
				})

		},
		iniciarEdicaoFuncionario(idFuncionario) {
			const vm = this;

			vm.limparFormulario();

			vm.$data.editandoFuncionario = true;
			vm.$data.idFuncionario = idFuncionario;

		},
		limparFormulario() {
			const vm = this;
			vm.$data.nome = "";
			vm.$data.setor = null;
			vm.$data.idade = null;
			vm.$data.salario = null;
			vm.$data.email = "";
		},
		mostraAlertaErro: function (erro, mensagem) {
			console.log(erro);
			alert(mensagem);
		},
		buscarSetores() {
			const vm = this;
			axios.get('/funcionarios/rest/setores')
				.then((response) => {
					vm.listaSetores = response.data;
				}).catch((error) => {
					vm.mostraAlertaErro("Erro interno", "Não foi possível lista setores")
				})
		}
	}
});