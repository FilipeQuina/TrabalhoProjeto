<%-- 
    Document   : addCompra
    Created on : 05/12/2016, 05:22:29
    Author     : Guilherme
--%>

<form action="cadastrarCompra.html" method="POST">
    <div>
        <label>Título:</label>
        <input name="nome" />
    </div>
    <div>
        <label>Escolha o cliente:<select name="cliente">
                <c:forEach var="cliente" items="${clientes}">
                    <option value="${cliente.id}">${cliente.nome}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div>
        <label>Escolha o DVD:<select name="dvd">
                <option value="0">Nenhum</option>
                <c:forEach var="dvd" items="${dvds}">
                    <option value="${dvd.id}">${dvd.nome}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div>
        <label>Escolha o BD:<select name="bd">
                <option value="0">Nenhum</option>
                <c:forEach var="bd" items="${bds}">
                    <option value="${bd.id}">${bd.nome}</option>
                </c:forEach>
            </select>
        </label>
    </div>

    <div>
        <label>Escolha a forma de pagamento:<select name="escolha">
                <option value="1">Boleto Bancário (O valor da compra é somado com 4)</option>
                <option value="2">Cartão de Crédito (O valor da compra é somado com 2)</option>
            </select>
        </label>
    </div>



    <div>
        <input type="submit" value="Enviar" />
    </div>
</form>
</body>
</html>
