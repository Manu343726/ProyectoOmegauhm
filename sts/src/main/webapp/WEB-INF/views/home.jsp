<%@ include file="../fragments/header.jspf" %>
<div id="principal">

<h1>Aplicaci�n de ejemplo de IW</h1>

<h3>En esta versi�n: "includes" jspf y soporte para sesiones</h3>

<ul>
	<li>admin / cualquier contrase�a de m�s de 3 caracteres = login de administrador</li>
	<li>cualquier otro login de m�s de 3 caracteres / contrase�a de m�s de 3 caracteres = usuario raso</li>
	<li>si pulsas "logout" se cierra la sesi�n</li>
</ul>

<h3>Cosas a mirar</h3>
<ul>
	<li>el c�digo de <code>src/main/webapp/WEB-INF/header.jspf</code>: una cabecera "rica", 
		uso de control de flujo JSP v�a "tags" de la JSP Standard Tag Library (JSTL): 
		if, choose/when/otherwise, uso de condiciones JSP Expression Language (EL))</li>
	<li>el c�digo de <code>src/main/webapp/WEB-INF/footer.jspf</code>: informaci�n de "debug" sobre contextos, 
		e iteraci�n sobre mapas usando EL</li>
	<li>si pulsas "m�s informaci�n" en el "footer" se pone una bandera "debug" en la sesi�n</li>
	<li>los recursos en <code>src/main/webapp/resources</code> est�n accesibles bajo 
		<code>mi-contexto/resources</code>; 
		aquellos bajo <code>src/main/webapp/WEB-INF</code> s�lo se pueden acceder desde dentro del JSP 
		(pero no externamente: no puedes acceder a 'header.jspf' cambiando la URL del navegador)</li>
</ul>

Pulsa en <a href="about">este enlace</a> para saber m�s sobre esta aplicaci�n.

</div>
<%@ include file="../fragments/footer.jspf" %>
