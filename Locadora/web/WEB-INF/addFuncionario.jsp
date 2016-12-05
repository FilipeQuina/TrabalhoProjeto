<%-- 
    Document   : addFuncionario
    Created on : 05/12/2016, 05:26:26
    Author     : Guilherme
--%>

<%@include file="cabecalho.jsp" %>
<h1>Formulário</h1>
<form action="cadastrarFuncionario.html" method="POST">
    <div>
        <label>Nome completo:</label>
        <input name="nome" />
    </div>

    <div>
        <label>Senha:</label>
        <input name="senha" />
    </div>

    <div>
        <label>Salario base:</label>
        <input name="salariobase" />
    </div>

    <div>
        <label>Escolha o cargo para o cálculo de imposto:<select name="cargo">
                    <option value="1">Padrão</option>
                    <option value="2">Gerente</option>
                    <option value="3">Secretário</option>
            </select>
        </label>
    </div>

    <div>
        <input type="submit" value="Enviar" />
    </div>
</form>
</body>
</html>
