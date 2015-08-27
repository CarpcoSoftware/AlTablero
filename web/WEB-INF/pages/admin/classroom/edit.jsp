<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Salones</title>
        <%@include file="../include_header.jsp" %>
        <link href="<c:url value="/res/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>
                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Salones<small><c:choose><c:when test="${empty classroom}">Crear</c:when><c:otherwise>Editar</c:otherwise></c:choose></small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-edit"></i> Salones</li>
                        <li class="active"><c:choose><c:when test="${empty classroom}">Crear</c:when><c:otherwise>Editar</c:otherwise></c:choose></li>
                    </ol>
                </section>
                <section class="content">
                    <c:choose>
                        <c:when test="${not empty classroom.yearBO.id && classroom.yearBO.id != currentYear.id}">
                           <c:set var="disabled" value='readonly="readonly"' />
                        </c:when>
                    </c:choose>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-tools">
                                <c:if test="${empty disabled}">
                                    <a href="#" id="save-link" class="btn btn-social-icon btn-success" style="margin: 8px; margin-left: 15px">
                                        <i class="fa fa-save" title="Guardar"></i>
                                    </a>
                                </c:if>
                                <c:if test="${not empty classroom || classroom.id > 0}">
                                    <a href="#" id="deactivate-link" class="btn btn-social-icon btn-google-plus" style="margin: 8px">
                                        <i class="fa fa-minus-circle" title="Eliminar"></i>
                                    </a>
                                </c:if>
                            </div>
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title"></h3>
                                </div>
                                <div class="box-body">
                                    <form id="frmClassRoomDelete" name="frmClassRoomDelete" method="POST"
                                          action="<c:url value='/admin/cursos/edicion/desactivar?${_csrf.parameterName}=${_csrf.token}' />">
                                        <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                    </form>
                                    <form id="frmClassRoomSave" name="frmClassRoomSave" method="POST"
                                          action="<c:url value='/admin/cursos/edicion/guardar?${_csrf.parameterName}=${_csrf.token}' />">
                                        <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                        <table style="width: 35%">
                                            <tr>
                                                <th>A&ntilde;o:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="divYear" class="form-group">
                                                        <select id="year" name="year" class="form-control" readonly="readonly" >
                                                            <c:forEach items="${years}" var="year">
                                                                <option value="${year.id}" <c:if test="${classroom.yearBO.id == year.id}">selected</c:if>>
                                                                    ${year.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Curso:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="divGrade" class="form-group">
                                                        <label id="lblGrade" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <select id="grade" name="grade" class="form-control" ${disabled}>
                                                            <option value="0">Seleccione uno...</option>
                                                            <c:forEach items="${grades}" var="grade">
                                                                <option value="${grade.id}" <c:if test="${classroom.gradeBO.id == grade.id}">selected</c:if>>
                                                                    ${grade.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Jornada:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="divTime" class="form-group">
                                                        <label id="lblTime" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <select id="time" name="time" class="form-control" ${disabled}>
                                                            <option value="0">Seleccione uno...</option>
                                                            <c:forEach items="${times}" var="time">
                                                                <option value="${time.id}" <c:if test="${classroom.timeBO.id == time.id}">selected</c:if>>
                                                                    ${time.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Director:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="divDirector" class="form-group">
                                                        <label id="lblDirector" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <select id="director" name="director" class="form-control" ${disabled}>
                                                            <option value="0">Seleccione uno...</option>
                                                            <c:forEach items="${teachers}" var="teacher">
                                                                <option value="${teacher.id}" <c:if test="${classroom.userBO.id == teacher.id}">selected</c:if>>
                                                                    ${teacher.name} ${teacher.lastName}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>C&oacute;digo del sal&oacute;n:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="divCode" class="form-group">
                                                        <label id="lblCode" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <input id="code" name="code" type="text" maxlength="5" class="form-control" placeholder="C&oacute;digo" value="${classroom.code}" autocomplete="off" ${disabled} />
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Nombre del sal&oacute;n:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="divName" class="form-group">
                                                        <label id="lblName" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <input id="name" name="name" type="text" maxlength="50" class="form-control" placeholder="Sal&oacute;n" value="${classroom.name}" autocomplete="off" ${disabled} />
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
                   
        <div id="save-dialog" title="Guardar">
            <c:choose>
                <c:when test="${empty classroom}">
                    <p>Un nuevo sal&oacute;n ser&aacute; creado, ¿Deseas cotinuar con la acci&oacute;n?</p>
                </c:when>
                <c:otherwise>
                    <p>El sal&oacute;n ser&aacute; actualizado, ¿Deseas cotinuar con la acci&oacute;n?</p>
                </c:otherwise>
            </c:choose>
        </div>
                                                
        <div id="deactivate-dialog" title="Eliminar">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:2px 7px 20px 0;"></span>El sal&oacute;n ser&aacute; eliminado, ¿Desea continuar con la acci&oacute;n?</p>
        </div>
                        
        <div id="required-dialog" title="Error">
            <p><span class="ui-icon ui-icon-cancel" style="float:left; margin:2px 7px 20px 0;"></span>Por favor complete todos los campos requeridos.</p>
        </div>
        
        <%@include file="../include_body_jscript.jsp" %>
        <script src="<c:url value="/res/javascript/jquery-ui.min.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#refClassRoom').trigger("click");
            } );
        </script>
        <script type="text/javascript">
            $( "#save-dialog" ).dialog({
                autoOpen: false,
                width: 400,
                modal: true,
                resizable: false,
                buttons: [{
                    text: "Guardar",
                    click: function() {
                        $( this ).dialog( "close" );
                        document.getElementById("frmClassRoomSave").submit();
                    }
                },
                {
                    text: "Cancelar",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }]
            });
            
            $( "#deactivate-dialog" ).dialog({
                autoOpen: false,
                width: 400,
                modal: true,
                resizable: false,
                buttons: [{
                    text: "Eliminar",
                    click: function() {
                        $( this ).dialog( "close" );
                        document.getElementById("frmClassRoomDelete").submit();
                    }
                },
                {
                    text: "Cancelar",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }]
            });
            
            $( "#required-dialog" ).dialog({
                autoOpen: false,
                width: 400,
                modal: true,
                resizable: false,
                buttons: [{
                    text: "Volver",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }]
            });

            $( "#save-link" ).click(function( event ) {
                if($.trim($("#code").val()) !== '' && $.trim($("#name").val()) !== '' && $("#year").val() !== '0' 
                        && $("#grade").val() !== '0' && $("#time").val() !== '0' && $("#director").val() !== '0') {
                    <c:choose>
                        <c:when test="${empty classroom}">
                            $( "#save-dialog" ).dialog( "open" );
                        </c:when>
                        <c:otherwise>
                            if($("#code").val() !== '${classroom.code}' || $("#name").val() !== '${classroom.name}'
                                || $("#year").val() !== '${classroom.yearBO.id}' || $("#grade").val() !== '${classroom.gradeBO.id}'
                                || $("#time").val() !== '${classroom.timeBO.id}' || $("#director").val() !== '${classroom.userBO.id}') {
                                $( "#save-dialog" ).dialog( "open" );
                            }
                        </c:otherwise>
                    </c:choose>
                } else {
                    $( "#required-dialog" ).dialog( "open" );
                }
                
                if ($("#year").val() === '0') {
                    document.getElementById("divYear").className = "form-group has-error";
                } else {
                    document.getElementById("divYear").className = "form-group";
                }
                
                if ($("#grade").val() === '0') {
                    document.getElementById("divGrade").className = "form-group has-error";
                    document.getElementById("lblGrade").style.display = 'block';
                } else {
                    document.getElementById("divGrade").className = "form-group";
                    document.getElementById("lblGrade").style.display = 'none';
                }
                
                if ($("#time").val() === '0') {
                    document.getElementById("divTime").className = "form-group has-error";
                    document.getElementById("lblTime").style.display = 'block';
                } else {
                    document.getElementById("divTime").className = "form-group";
                    document.getElementById("lblTime").style.display = 'none';
                }
                
                if ($("#director").val() === '0') {
                    document.getElementById("divDirector").className = "form-group has-error";
                    document.getElementById("lblDirector").style.display = 'block';
                } else {
                    document.getElementById("divDirector").className = "form-group";
                    document.getElementById("lblDirector").style.display = 'none';
                }
                
                if($.trim($("#code").val()) === '') {
                    document.getElementById("divCode").className = "form-group has-error";
                    document.getElementById("lblCode").style.display = 'block';
                } else {
                    document.getElementById("divCode").className = "form-group";
                    document.getElementById("lblCode").style.display = 'none';
                }
                
                if ($("#name").val() === '') {
                    document.getElementById("divName").className = "form-group has-error";
                    document.getElementById("lblName").style.display = 'block';
                } else {
                    document.getElementById("divName").className = "form-group";
                    document.getElementById("lblName").style.display = 'none';
                }
                event.preventDefault();
            });
            
            $( "#deactivate-link" ).click(function( event ) {
                $( "#deactivate-dialog" ).dialog( "open" );
                event.preventDefault();
            });
        </script>
    </body>
</html>
