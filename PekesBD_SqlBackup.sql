USE [master]
GO
/****** Object:  Database [PekesBD]    Script Date: 03/12/2016 05:25:05 p. m. ******/
CREATE DATABASE [PekesBD]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PekesBD', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PekesBD.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PekesBD_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PekesBD_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PekesBD] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PekesBD].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PekesBD] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PekesBD] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PekesBD] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PekesBD] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PekesBD] SET ARITHABORT OFF 
GO
ALTER DATABASE [PekesBD] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [PekesBD] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PekesBD] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PekesBD] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PekesBD] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PekesBD] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PekesBD] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PekesBD] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PekesBD] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PekesBD] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PekesBD] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PekesBD] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PekesBD] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PekesBD] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PekesBD] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PekesBD] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PekesBD] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PekesBD] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PekesBD] SET  MULTI_USER 
GO
ALTER DATABASE [PekesBD] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PekesBD] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PekesBD] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PekesBD] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PekesBD] SET DELAYED_DURABILITY = DISABLED 
GO
USE [PekesBD]
GO
/****** Object:  UserDefinedFunction [dbo].[ObtenerIdHolder]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date, ,>
-- Description:	<Description, ,>
-- =============================================
create FUNCTION [dbo].[ObtenerIdHolder]
(
	
)
RETURNS bigint
AS
BEGIN
	-- Declare the return variable here
	DECLARE @Resultado bigint = 0

	-- Add the T-SQL statements to compute the return value here
	SELECT  @Resultado = sesu_valor FROM dbo.IdHolder
	WHERE sesu_id = @@SPID

	-- Return the result of the function
	RETURN @Resultado

END

GO
/****** Object:  Table [dbo].[Calzado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Calzado](
	[pk_IdCalzado] [int] IDENTITY(1,1) NOT NULL,
	[Codigo] [varchar](10) NOT NULL,
	[Marca] [varchar](16) NOT NULL,
	[Especificaciones] [text] NULL,
	[fk_IdFoto] [bigint] NULL,
	[Estatus] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[pk_IdCalzado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cliente](
	[pk_IdCliente] [int] IDENTITY(1,1) NOT NULL,
	[FolioCliente] [varchar](50) NULL,
	[NombreContacto] [varchar](50) NOT NULL,
	[Categoria] [varchar](50) NOT NULL,
	[Estatus] [tinyint] NOT NULL,
	[fk_IdPersona] [int] NOT NULL,
 CONSTRAINT [PK__Cliente__034C44014EDA4B3C] PRIMARY KEY CLUSTERED 
(
	[pk_IdCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Credencial]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Credencial](
	[pk_IdCredencial] [bigint] IDENTITY(1,1) NOT NULL,
	[Nick] [varchar](250) NOT NULL,
	[Psw] [varchar](500) NOT NULL,
	[Estatus] [smallint] NOT NULL,
	[fk_IdPersona] [int] NOT NULL,
	[fk_IdCreador] [int] NULL,
 CONSTRAINT [PK__Credenci__D009BB99AD6F5061] PRIMARY KEY CLUSTERED 
(
	[pk_IdCredencial] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DetallesPedido]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DetallesPedido](
	[fk_IdPedido] [bigint] NOT NULL,
	[fk_IdCalzado] [int] NOT NULL,
	[fk_IdEstilo] [int] NOT NULL,
	[Suela] [varchar](50) NOT NULL,
	[Costo] [float] NOT NULL,
	[NumPares] [int] NULL,
	[Talla] [float] NULL,
	[pk_DetallesPedido] [int] NOT NULL,
	[Observaciones] [varchar](500) NULL,
 CONSTRAINT [PK_DetallesPedido] PRIMARY KEY CLUSTERED 
(
	[pk_DetallesPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Empleado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Empleado](
	[pk_IdEmpleado] [int] IDENTITY(1,1) NOT NULL,
	[FolioEmpleado] [varchar](50) NOT NULL,
	[FechaIngreso] [date] NOT NULL,
	[Puesto] [varchar](50) NOT NULL,
	[FechaEgreso] [date] NULL,
	[fk_IdPersona] [int] NOT NULL,
	[Estatus] [tinyint] NOT NULL,
 CONSTRAINT [PK__Empleado__B5BD0539A6D30F48] PRIMARY KEY CLUSTERED 
(
	[pk_IdEmpleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Estilo]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Estilo](
	[pk_IdEstilo] [int] IDENTITY(1,1) NOT NULL,
	[Material] [varchar](100) NOT NULL,
	[Color] [varchar](100) NOT NULL,
	[Estatus] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[pk_IdEstilo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Galeria]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Galeria](
	[pk_IdGaleria] [bigint] IDENTITY(1,1) NOT NULL,
	[Codigo] [varchar](100) NOT NULL,
	[Foto] [text] NOT NULL,
	[Descripcion] [varchar](140) NOT NULL,
	[Estatus] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[pk_IdGaleria] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[IdHolder]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IdHolder](
	[sesu_id] [bigint] NOT NULL,
	[sesu_valor] [bigint] NOT NULL,
 CONSTRAINT [PK_ID_HOLDER] PRIMARY KEY CLUSTERED 
(
	[sesu_id] ASC,
	[sesu_valor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Pedido]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pedido](
	[pk_IdPedido] [bigint] IDENTITY(1,1) NOT NULL,
	[Folio] [bigint] NULL,
	[FechaSolicitud] [date] NOT NULL,
	[FechaEntrega] [date] NOT NULL,
	[Estado] [varchar](30) NOT NULL,
	[fk_IdVenta] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[pk_IdPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Persona]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Persona](
	[pk_IdPersona] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[ApellidoPaterno] [varchar](50) NOT NULL,
	[ApellidoMaterno] [varchar](50) NOT NULL,
	[Rfc] [varchar](13) NULL,
	[Curp] [varchar](20) NULL,
	[Domicilio] [varchar](100) NOT NULL,
	[Ciudad] [varchar](50) NOT NULL,
	[CodigoPostal] [varchar](20) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[TelMovil] [text] NOT NULL,
	[Fotografia] [text] NULL,
	[FechaNacimiento] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[pk_IdPersona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Sesion]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Sesion](
	[pk_IdSesion] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_IdCredencial] [bigint] NOT NULL,
	[Token] [varchar](50) NOT NULL,
	[FechaInicio] [datetime] NOT NULL,
	[UltimaActividad] [datetime] NULL,
	[FechaFinal] [datetime] NULL,
	[Estatus] [tinyint] NOT NULL,
 CONSTRAINT [PK_Sesion] PRIMARY KEY CLUSTERED 
(
	[pk_IdSesion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Venta]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Venta](
	[pk_IdVenta] [bigint] IDENTITY(1,1) NOT NULL,
	[Subtotal] [float] NOT NULL,
	[Descuento] [float] NOT NULL,
	[Impuestos] [float] NOT NULL,
	[Total] [float] NOT NULL,
	[Liquidado] [tinyint] NOT NULL,
	[fk_IdComprador] [int] NOT NULL,
	[Estatus] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[pk_IdVenta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  View [dbo].[V_Cliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[V_Cliente]
AS
SELECT C.FolioCliente, C.NombreContacto, C.Categoria, C.Estatus, P.Nombre, P.ApellidoPaterno, P.ApellidoMaterno, P.Rfc, P.Curp, P.Domicilio, P.Ciudad, P.CodigoPostal, P.Email, P.TelMovil, P.Fotografia, P.FechaNacimiento, C.pk_IdCliente, P.pk_IdPersona
FROM  dbo.Cliente AS C INNER JOIN
         dbo.Persona AS P ON C.fk_IdPersona = P.pk_IdPersona

GO
ALTER TABLE [dbo].[Calzado]  WITH CHECK ADD FOREIGN KEY([fk_IdFoto])
REFERENCES [dbo].[Galeria] ([pk_IdGaleria])
GO
ALTER TABLE [dbo].[Cliente]  WITH CHECK ADD  CONSTRAINT [FK__Cliente__fk_IdPe__1273C1CD] FOREIGN KEY([fk_IdPersona])
REFERENCES [dbo].[Persona] ([pk_IdPersona])
GO
ALTER TABLE [dbo].[Cliente] CHECK CONSTRAINT [FK__Cliente__fk_IdPe__1273C1CD]
GO
ALTER TABLE [dbo].[Credencial]  WITH CHECK ADD  CONSTRAINT [FK__Credencia__fk_IdEmpleado] FOREIGN KEY([fk_IdCreador])
REFERENCES [dbo].[Empleado] ([pk_IdEmpleado])
GO
ALTER TABLE [dbo].[Credencial] CHECK CONSTRAINT [FK__Credencia__fk_IdEmpleado]
GO
ALTER TABLE [dbo].[Credencial]  WITH CHECK ADD  CONSTRAINT [FK__Credencia__fk_IdPersona] FOREIGN KEY([fk_IdPersona])
REFERENCES [dbo].[Persona] ([pk_IdPersona])
GO
ALTER TABLE [dbo].[Credencial] CHECK CONSTRAINT [FK__Credencia__fk_IdPersona]
GO
ALTER TABLE [dbo].[DetallesPedido]  WITH CHECK ADD  CONSTRAINT [FK__DetallesP__fk_Id__29572725] FOREIGN KEY([fk_IdPedido])
REFERENCES [dbo].[Pedido] ([pk_IdPedido])
GO
ALTER TABLE [dbo].[DetallesPedido] CHECK CONSTRAINT [FK__DetallesP__fk_Id__29572725]
GO
ALTER TABLE [dbo].[DetallesPedido]  WITH CHECK ADD  CONSTRAINT [FK__DetallesP__fk_Id__2A4B4B5E] FOREIGN KEY([fk_IdCalzado])
REFERENCES [dbo].[Calzado] ([pk_IdCalzado])
GO
ALTER TABLE [dbo].[DetallesPedido] CHECK CONSTRAINT [FK__DetallesP__fk_Id__2A4B4B5E]
GO
ALTER TABLE [dbo].[DetallesPedido]  WITH CHECK ADD  CONSTRAINT [FK__DetallesP__fk_Id__2B3F6F97] FOREIGN KEY([fk_IdEstilo])
REFERENCES [dbo].[Estilo] ([pk_IdEstilo])
GO
ALTER TABLE [dbo].[DetallesPedido] CHECK CONSTRAINT [FK__DetallesP__fk_Id__2B3F6F97]
GO
ALTER TABLE [dbo].[Empleado]  WITH CHECK ADD  CONSTRAINT [FK__Empleado__fk_IdP__15502E78] FOREIGN KEY([fk_IdPersona])
REFERENCES [dbo].[Persona] ([pk_IdPersona])
GO
ALTER TABLE [dbo].[Empleado] CHECK CONSTRAINT [FK__Empleado__fk_IdP__15502E78]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD FOREIGN KEY([fk_IdVenta])
REFERENCES [dbo].[Venta] ([pk_IdVenta])
GO
ALTER TABLE [dbo].[Sesion]  WITH CHECK ADD  CONSTRAINT [FK__Credencia__fk_IdCredencial] FOREIGN KEY([fk_IdCredencial])
REFERENCES [dbo].[Credencial] ([pk_IdCredencial])
GO
ALTER TABLE [dbo].[Sesion] CHECK CONSTRAINT [FK__Credencia__fk_IdCredencial]
GO
ALTER TABLE [dbo].[Venta]  WITH CHECK ADD FOREIGN KEY([fk_IdComprador])
REFERENCES [dbo].[Persona] ([pk_IdPersona])
GO
/****** Object:  StoredProcedure [dbo].[CrearIdHolder]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
create PROCEDURE [dbo].[CrearIdHolder]
	@Valor bigint
AS
BEGIN

	SET NOCOUNT ON;

	INSERT INTO dbo.IdHolder
	(sesu_id,sesu_valor) VALUES (@@SPID, @Valor);
END

GO
/****** Object:  StoredProcedure [dbo].[pa_AutenticarCredencial]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_AutenticarCredencial] 
	@Nick varchar(250),
	@Pwd  varchar(500)
AS
BEGIN
	SET NOCOUNT ON;
    DECLARE @RespuestaXML xml
	DECLARE @UsuarioXML xml
	DECLARE @SesionXML xml
	DECLARE @IdUsuario as bigint
	DECLARE @UsuarioStatus as bit
	DECLARE @SesionValida as tinyint
	DECLARE @TOKEN as varchar(50)
	
	
	SET @RespuestaXML = '<Usuarios><Sesion></Sesion><Usuario></Usuario></Usuarios>'
	SELECT @UsuarioXML =(
		SELECT *
		FROM dbo.Credencial
		WHERE Nick = @Nick AND Psw = @Pwd AND Estatus = 1
		FOR XML PATH(''),ELEMENTS)
	SET @RespuestaXML.modify('insert sql:variable("@UsuarioXML") as last into (/Usuarios/Usuario)[1]')
	
	SELECT @IdUsuario = ParamValues.id.value('pk_IdCredencial[1]','VARCHAR(100)')
	FROM @RespuestaXML.nodes('/Usuarios/Usuario') as ParamValues(ID);
	IF @IdUsuario > 0
	BEGIN
		SET @TOKEN = SUBSTRING(master.dbo.fn_varbintohexstr
		(HashBytes('sha1',CONVERT(varchar(12),@IdUsuario) +
		CONVERT(varchar(50),GETDATE(),120))),3,32)
		EXEC dbo.pa_ValidarSesion @IdUsuario,@TOKEN,@SesionXML out
	END
	ELSE
		SET @SesionXML = '<Valido>0</Valido><Mensaje>USUARIO INCORRECTO</Mensaje>'
	SET @RespuestaXML.modify('insert sql:variable("@SesionXML") as last into (/Usuarios/Sesion)[1]')
	SELECT @RespuestaXML AS Respuesta
END
GO
/****** Object:  StoredProcedure [dbo].[pa_CerrarSesion]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_CerrarSesion]
	@TOKEN varchar(50)
	
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @message xml
	UPDATE dbo.Sesion 
	SET Estatus = 0, FechaFinal = GETDATE()
	WHERE Token LIKE @TOKEN;

	SET @message = '<Valido>1</Valido><Mensaje>SESION CERRADA</Mensaje><SesionInfo></SesionInfo>'; 
	SELECT @message;
END
GO
/****** Object:  StoredProcedure [dbo].[pa_InsertarCliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_InsertarCliente]
	
	@Nombre varchar(50), 
	@ApellidoPaterno varchar(50), 
	@ApellidoMaterno varchar(50), 
	@Rfc varchar(13), 
	@Curp varchar(20),
	@Domicilio varchar(100),
	@Ciudad varchar(50), 
	@CodigoPostal varchar(50),
	@Email varchar(50),
	@TelMovil text,
	@Fotografia text,
	@FechaNacimiento varchar(50),
	
	@Folio varchar(50),
	@NomContacto varchar(50),
	@Categoria varchar(50)
	 

AS
BEGIN

	SET NOCOUNT ON;

	BEGIN TRAN Trans
	BEGIN TRY 
    
	DECLARE @Id_persona bigint

    INSERT INTO dbo.Persona(Nombre, ApellidoPaterno, ApellidoMaterno, Rfc, Curp, Domicilio, Ciudad, CodigoPostal, 
	Email, TelMovil, Fotografia, FechaNacimiento)

	VALUES (@Nombre, @ApellidoPaterno, @ApellidoMaterno, @Rfc, @Curp, @Domicilio, @Ciudad, @CodigoPostal, @Email,
	@TelMovil, @Fotografia, cast(@FechaNacimiento as date)); 	

	SET @Id_persona = dbo.ObtenerIdHolder()

	EXEC dbo.pa_LiberarHolder

	INSERT INTO dbo.Cliente(FolioCliente, NombreContacto, Categoria, fk_IdPersona, Estatus)
	VALUES (@Folio, @NomContacto, @Categoria, @Id_persona, 1)

	COMMIT TRAN Trans 
	END TRY 
	BEGIN CATCH 
	ROLLBACK TRAN Trans 
	SELECT @@ERROR
	END CATCH 
END
GO
/****** Object:  StoredProcedure [dbo].[pa_InsertarCredencial]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_InsertarCredencial]
	@Nick varchar(250),
	@Pwd varchar(500),
	@TOKEN varchar(150)
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @IDcreador int;

	SET @IDcreador = (SELECT sesi_credId FROM Sesiones WHERE sesi_token LIKE @TOKEN);
	INSERT INTO Credencial(Nick,Psw,Estatus,fk_IdCreador)
	VALUES (@Nick, @Pwd,1,@IDcreador);
	
END

GO
/****** Object:  StoredProcedure [dbo].[pa_InsertarDetallesPedido]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_InsertarDetallesPedido]
	
	@fk_IdCalzado int,
	@fk_Estilo int,
	@Suela varchar (50),
	@Costo float,
	@Talla float,
	@NumPares int,
	@Observaciones varchar (250)
 
AS
BEGIN

	SET NOCOUNT ON;

	BEGIN TRAN Trans
	BEGIN TRY 
    
	DECLARE @id_pedido bigint
	set @id_pedido =(SELECT MAX(pk_IdPedido) AS id FROM Pedido)

   INSERT INTO dbo.DetallesPedido(fk_IdPedido, fk_IdCalzado, fk_IdEstilo, Suela, Costo, Talla, NumPares, Observaciones)
	VALUES (@id_pedido, @fk_IdCalzado, @fk_Estilo, @Suela, @Costo, @Talla, @NumPares, @Observaciones)

	COMMIT TRAN Trans 
	END TRY 
	BEGIN CATCH 
	ROLLBACK TRAN Trans 
	SELECT @@ERROR
	END CATCH 
END


GO
/****** Object:  StoredProcedure [dbo].[pa_InsertarEmpleado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_InsertarEmpleado]
	
	@Nombre varchar(50), 
	@ApellidoPaterno varchar(50), 
	@ApellidoMaterno varchar(50), 
	@Rfc varchar(13), 
	@Curp varchar(20),
	@Domicilio varchar(100),
	@Ciudad varchar(50), 
	@CodigoPostal varchar(50),
	@Email varchar(50),
	@TelMovil text,
	@FechaNacimiento varchar(50),
	
	@FolioEmpleado varchar(50),
	@FechaIngreso varchar(50),
	@Puesto varchar(50),
	@FechaEgreso varchar(50)
	 

AS
BEGIN

	SET NOCOUNT ON;

	BEGIN TRAN Trans
	BEGIN TRY 
    
	DECLARE @Id_persona bigint

    INSERT INTO dbo.Persona(Nombre, ApellidoPaterno, ApellidoMaterno, Rfc, Curp, Domicilio, Ciudad, CodigoPostal, 
	Email, TelMovil, FechaNacimiento)

	VALUES (@Nombre, @ApellidoPaterno, @ApellidoMaterno, @Rfc, @Curp, @Domicilio, @Ciudad, @CodigoPostal, @Email,
	@TelMovil, cast(@FechaNacimiento as date)); 	

	SET @Id_persona = dbo.ObtenerIdHolder()

	EXEC dbo.pa_LiberarHolder

	INSERT INTO dbo.Empleado(FolioEmpleado, FechaIngreso, Puesto, FechaEgreso, fk_IdPersona, Estatus)
	VALUES (@FolioEmpleado, cast( @FechaIngreso as date), @Puesto, cast( @FechaEgreso as date), @Id_persona, 1)

	COMMIT TRAN Trans 
	END TRY 
	BEGIN CATCH 
	ROLLBACK TRAN Trans 
	SELECT @@ERROR
	END CATCH 
END


GO
/****** Object:  StoredProcedure [dbo].[pa_InsertarVenta]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[pa_InsertarVenta]
	@Subtotal float,
	@Descuento float,
	@Impuestos float,
	@Total float,
	@Liquidado tinyint,
	@fk_IdComprador int
AS
BEGIN
	
	SET NOCOUNT ON;
	BEGIN TRAN transaccion
	BEGIN TRY

    INSERT INTO Venta (Subtotal,Descuento,Impuestos,Total, Liquidado, fk_IdComprador, Estatus)
	VALUES(@Subtotal,@Descuento,@Impuestos,@Total, @Liquidado, @fk_IdComprador, 1)

	COMMIT TRAN transaccion
	END TRY 
	BEGIN CATCH 
	ROLLBACK TRAN transaccion
	SELECT @@ERROR 
	END CATCH
END

GO
/****** Object:  StoredProcedure [dbo].[pa_java_ActualizarCliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_ActualizarCliente] 
	-- Add the parameters for the stored procedure here
	(
	@Nombre varchar(50), 
	@ApellidoPaterno varchar(50), 
	@ApellidoMaterno varchar(50), 
	@Rfc varchar(13), 
	@Curp varchar(20),

	@Domicilio varchar(100),
	@Ciudad varchar(50), 
	@CodigoPostal varchar(50),
	@Email varchar(50),
	@TelMovil text,

	@Fotografia text,
	@FechaNacimiento varchar(50),
	@IdPersona int,
	@Folio varchar(50),
	@NomContac varchar(50),
	@Categoria varchar(50)
	)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Persona Set Nombre=@Nombre,ApellidoPaterno=@ApellidoPaterno,ApellidoMaterno=@ApellidoMaterno,
			Rfc=@Rfc,Curp=@Curp,Domicilio=@Domicilio,Ciudad=@Ciudad,CodigoPostal=@CodigoPostal, 
	Email=@Email,TelMovil=@TelMovil, Fotografia=@Fotografia,FechaNacimiento= @FechaNacimiento -- Cast(@FechaNacimiento AS Date)
	WHERE pk_IdPersona=@IdPersona;


	UPDATE Cliente SET FolioCliente=@Folio, NombreContacto=@NomContac, Categoria=@Categoria
	Where fk_IdPersona=@IdPersona
	End


GO
/****** Object:  StoredProcedure [dbo].[pa_java_ActualizarEmpleado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_ActualizarEmpleado] 
	-- Add the parameters for the stored procedure here
	(
	@Nombre varchar(50), 
	@ApellidoPaterno varchar(50), 
	@ApellidoMaterno varchar(50), 
	@Rfc varchar(13), 
	@Curp varchar(20),
	@Domicilio varchar(100),
	@Ciudad varchar(50), 
	@CodigoPostal varchar(50),
	@Email varchar(50),
	@TelMovil text,
	@Fotografia text,
	@FechaNacimiento varchar(50),
	@IdPersona int,
	
	@Folio varchar(50),
	@FechaIngreso varchar(50),
	@Puesto varchar(50),
	@FechaEgreso varchar(50)
	)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Persona Set Nombre=@Nombre,ApellidoPaterno=@ApellidoPaterno,ApellidoMaterno=@ApellidoMaterno,
			Rfc=@Rfc,Curp=@Curp,Domicilio=@Domicilio,Ciudad=@Ciudad,CodigoPostal=@CodigoPostal, 
	Email=@Email,TelMovil=@TelMovil, Fotografia=@Fotografia,FechaNacimiento= Cast(@FechaNacimiento AS Date)
	WHERE pk_IdPersona=@IdPersona;


	UPDATE Empleado SET FolioEmpleado=@Folio, FechaIngreso=Cast(@FechaIngreso As date), Puesto=@Puesto, FechaEgreso =Cast(@FechaEgreso As date)
	Where fk_IdPersona=@IdPersona
END



GO
/****** Object:  StoredProcedure [dbo].[pa_java_ActualizarGaleria]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_ActualizarGaleria]
	@Identificador bigint,
	@Codigo varchar(100),
	@Foto text,
	@Decripcion varchar(140),
	@Estatus tinyint
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

		UPDATE Galeria SET Codigo = @Codigo,
						   Foto = @Foto,
						   Descripcion = @Decripcion,
						   Estatus= @Estatus
		WHERE pk_IdGaleria = @Identificador	  
END



GO
/****** Object:  StoredProcedure [dbo].[pa_java_ActualizarPedido]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pa_java_ActualizarPedido] 
	-- Add the parameters for the stored procedure here
	(
	@pk_IdDetallesPedido bigint,
	@FechaEntrega varchar(50),
	@FechaSolicutud varchar(50),
	@fk_IdPedido bigint,
	@Folio int,

	@Suela varchar(50),
	@Costo float,
	@Observaciones varchar(250),
	@Talla float,
	@NumPares int,
	@Codigo varchar(50)
	)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @fk_IdCalzado int;

	set @fk_IdCalzado = (select pk_IdCalzado from Calzado where Codigo=@Codigo)

    -- Insert statements for procedure here
	UPDATE Pedido Set Folio=@Folio,FechaSolicitud=cast(@FechaSolicutud as date), FechaEntrega=cast(@FechaEntrega as date)
	WHERE pk_IdPedido=@fk_IdPedido;


	UPDATE DetallesPedido SET Suela=@Suela, fk_IdCalzado=@fk_IdCalzado ,Costo=@Costo, Talla=@Talla, NumPares=@NumPares, Observaciones=@Observaciones
	Where pk_DetallesPedido=@pk_IdDetallesPedido
	End



GO
/****** Object:  StoredProcedure [dbo].[pa_java_BorrarCliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pa_java_BorrarCliente]
	@IdCliente int
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE Cliente set Estatus=0
	WHERE pk_IdCliente =@IdCliente
END
GO
/****** Object:  StoredProcedure [dbo].[pa_java_BorrarEmpleado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pa_java_BorrarEmpleado]
	@IdPersona int
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE Empleado set Estatus=0
	WHERE fk_IdPersona =@IdPersona
END

GO
/****** Object:  StoredProcedure [dbo].[pa_java_ConsultaCliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pa_java_ConsultaCliente]
	@IdCliente int
AS
BEGIN
	SET NOCOUNT ON;
	SELECT 
	P.Nombre, P.ApellidoPaterno, P.ApellidoMaterno, P.Rfc, 
	P.Domicilio,P.Ciudad, P.CodigoPostal, P.Email, P.TelMovil,
	P.Fotografia, P.FechaNacimiento, C.FolioCliente, C.NombreContacto, C.Categoria
	FROM Persona AS P INNER JOIN Cliente AS C
	ON  C.fk_IdPersona = P.pk_IdPersona
	WHERE C.pk_IdCliente = @IdCliente;
END
GO
/****** Object:  StoredProcedure [dbo].[pa_java_ConsultarGaleria]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_ConsultarGaleria]
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

		SELECT * FROM Galeria
END



GO
/****** Object:  StoredProcedure [dbo].[pa_java_ConsultarPedidos]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


create PROCEDURE [dbo].[pa_java_ConsultarPedidos]
	@pk_IdPedido int
AS
BEGIN
	SET NOCOUNT ON;
	SELECT D.pk_DetallesPedido, P.pk_IdPedido, P.Folio, P.fk_idVenta, C.Codigo, C.Marca, D.fk_IdEstilo, D.Suela, D.Talla, D.NumPares, D.Costo, P.FechaSolicitud, P.FechaEntrega, Estado, D.Observaciones
	 FROM Pedido P INNER JOIN DetallesPedido D ON pk_IdPedido = D.fk_IdPedido inner join  Calzado C ON fk_IdCalzado=pk_IdCalzado where P.pk_IdPedido=@pk_IdPedido
END


GO
/****** Object:  StoredProcedure [dbo].[pa_java_ConsultarVentas]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_ConsultarVentas]
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

		SELECT v.pk_IdVenta,v.Subtotal,v.Descuento,v.Impuestos,v.Total,v.Liquidado,
			   c.NombreContacto,c.FolioCliente
			/*   p.Folio,p.FechaSolicitud,p.FechaEntrega*/
	    FROM Venta v 
		/*INNER JOIN Pedido p ON p.fk_IdVenta = v.pk_IdVenta*/
		INNER JOIN Cliente c ON c.pk_IdCliente = v.fk_IdComprador
END



GO
/****** Object:  StoredProcedure [dbo].[pa_java_ConsultarVentas_PorNombreCliente]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_ConsultarVentas_PorNombreCliente]
	@NombreContacto varchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

		SELECT v.pk_IdVenta,v.Subtotal,v.Descuento,v.Impuestos,v.Total,v.Liquidado,
			   c.NombreContacto,c.FolioCliente
			/*   p.Folio,p.FechaSolicitud,p.FechaEntrega*/
	    FROM Venta v 
		
		/*INNER JOIN Pedido p ON p.fk_IdVenta = v.pk_IdVenta*/
		INNER JOIN Cliente c ON c.pk_IdCliente = v.fk_IdComprador
		WHERE  c.NombreContacto LIKE @NombreContacto
END



GO
/****** Object:  StoredProcedure [dbo].[pa_java_ConsultaTodoElCalzado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pa_java_ConsultaTodoElCalzado]
	
AS
BEGIN
	SET NOCOUNT ON;
	SELECT C.Codigo, C.Especificaciones, C.Estatus, C.pk_IdCalzado
	FROM Calzado C
END
GO
/****** Object:  StoredProcedure [dbo].[pa_java_InsertarCalzado]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_InsertarCalzado]
	
	@Codigo varchar(50), 
	@Especificaciones varchar(50), 
	@Marca varchar(50), 
	@PreciosXML xml, 
	@Estatus int,

	@Foto_Codigo varchar(50),
	@Foto_foto text,
	@Foto_Descripcion varchar(50)
AS
BEGIN

	SET NOCOUNT ON;

	BEGIN TRAN Trans
	BEGIN TRY 
    
	DECLARE @Id_fotografia bigint

    INSERT INTO dbo.Galeria(Codigo,Descripcion,Foto,Estatus)

	VALUES (@Foto_Codigo,@Foto_Descripcion,@Foto_foto,1); 	

	SET @Id_fotografia = dbo.ObtenerIdHolder()
	DECLARE @xml xml = '<xml/>'
	EXEC dbo.pa_LiberarHolder
	SET @PreciosXML = '<xml></xml>';
	INSERT INTO dbo.Calzado(Codigo,Especificaciones,fk_IdFoto,Marca,PreciosXML, Estatus)
	VALUES (@Codigo, @Especificaciones, @Id_fotografia, @Marca,@xml, 1)
	SELECT 'OperacionExitosa';
	COMMIT TRAN Trans 
	END TRY 
	BEGIN CATCH 
	ROLLBACK TRAN Trans 
	SELECT @@ERROR
	END CATCH 
END
GO
/****** Object:  StoredProcedure [dbo].[pa_java_InsertarFotografia]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_java_InsertarFotografia]
	@Codigo varchar(100),
	@Descripcion varchar(140),
	@Foto text,
	@Estatus tinyint
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO Galeria VALUES(@Codigo,@Foto,@Descripcion,@Estatus)
END


GO
/****** Object:  StoredProcedure [dbo].[pa_java_MostrarClientes]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_java_MostrarClientes]
	
AS
BEGIN
	SET NOCOUNT ON;
	
	SELECT pk_IdCliente, FolioCliente, NombreContacto, Categoria, Estatus, P.* FROM Cliente INNER JOIN Persona P ON fk_IdPersona = P.pk_IdPersona

END
GO
/****** Object:  StoredProcedure [dbo].[pa_java_MostrarEmpleados]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_java_MostrarEmpleados]
	
AS
BEGIN
	SET NOCOUNT ON;
	
	SELECT pk_IdEmpleado, FolioEmpleado, FechaIngreso, Puesto, FechaEgreso, Estatus, P.* FROM Empleado INNER JOIN Persona P ON fk_IdPersona = P.pk_IdPersona

END

GO
/****** Object:  StoredProcedure [dbo].[pa_LiberarHolder]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_LiberarHolder]
	
AS
BEGIN

	SET NOCOUNT ON;

	DELETE FROM dbo.IdHolder
	WHERE sesu_id = @@SPID
END

GO
/****** Object:  StoredProcedure [dbo].[pa_ValidarAccesoSesion]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[pa_ValidarAccesoSesion]
@TokenSesion varchar(50),
@RespuestaXML xml output
AS
BEGIN
	DECLARE @PermisoXML xml
	DECLARE @ValidoXML xml
	DECLARE @FechaSesion datetime
	DECLARE @Multisesion bigint
	DECLARE @FechaMenor datetime
	DECLARE @FechaMayor datetime
	DECLARE @IdentificadorSesion bigint
	DECLARE @IdCredencial smallint
	SET @FechaMenor=DATEADD(n,-5,GETDATE()); --SE ESTABLECE EL TIEMPO LIMITE DEL USUARIO
	SET @FechaMayor=DATEADD(n,5,GETDATE());
	SET @RespuestaXML = '<Usuario><Validez></Validez><Ticket></Ticket></Usuario>'
	SELECT @IdentificadorSesion = pk_IdSesion,@IdCredencial = fk_IdCredencial
	FROM dbo.Sesion
	WHERE Token = @TokenSesion AND
	(
	UltimaActividad > @FechaMenor AND
	UltimaActividad < @FechaMayor
	) AND Estatus = 1;
	IF(@IdCredencial >0)
	BEGIN
		UPDATE dbo.Sesion
		SET UltimaActividad = GETDATE() WHERE Token = @TokenSesion;
		SET @ValidoXML = '<Valido>1</Valido>';
		SET @PermisoXML = 
		(
		SELECT @IdCredencial AS IdentificadorUsuario
		FOR XML PATH(''), ELEMENTS
		)
		END
		ELSE
		BEGIN
		SET @ValidoXML = '<Valido>0</Valido>';
		--Cierra e invalida el token
		UPDATE dbo.Sesion
		SET FechaFinal = GETDATE(),
		Estatus = 0 WHERE Token = @TokenSesion;
		END
		SET @RespuestaXML.modify('insert sql:variable("@ValidoXML") as last into (Usuario/Validez)[1]')
		SET @RespuestaXML.modify('insert sql:variable("@PermisoXML") as last into (/Usuario/Ticket)[1]')
END
GO
/****** Object:  StoredProcedure [dbo].[pa_ValidarSesion]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pa_ValidarSesion]
	@IdUsuario bigint,
	@TOKEN varchar(50),
	@SesionXML xml output
AS
BEGIN
	
	SET NOCOUNT ON;
	DECLARE @Count int = 1
	DECLARE @InfoXML xml
	SELECT @Count = COUNT(fk_IdCredencial)
	FROM dbo.Sesion 
	WHERE fk_IdCredencial = @IdUsuario AND Estatus = 1;
	IF @Count <= 1 
	BEGIN 
		IF @Count = 0
		BEGIN
	INSERT INTO dbo.Sesion
           (fk_IdCredencial
		   ,Token
           ,FechaInicio
           ,UltimaActividad
           ,Estatus)
     VALUES
           (@IdUsuario
           ,@TOKEN
           ,GETDATE()
           ,GETDATE()
           ,1
		   )
		    SET @SesionXML = '<Valido>1</Valido><Mensaje>NUEVA SESION</Mensaje><SesionInfo></SesionInfo>'
		END
		ELSE
		BEGIN
			UPDATE dbo.Sesion
			SET Token = @TOKEN,
			UltimaActividad = GETDATE()
			WHERE fk_IdCredencial = @IdUsuario AND Estatus = 1;
			
			SET @SesionXML = '<Valido>1</Valido><Mensaje>SESION ACTUALIZADA</Mensaje><SesionInfo></SesionInfo>'	
		END
		--Obtener la infrormacion de la sesion
		SELECT @InfoXML =
		(
			SELECT dbo.Sesion .*, @IdUsuario as IdUsuario From dbo.Sesion
			WHERE fk_IdCredencial = @IdUsuario
			 AND Token = @TOKEN
			 AND Estatus = 1
			FOR XML PATH (''),ELEMENTS
		)
	END
	ELSE
	BEGIN
		 UPDATE dbo.Sesion SET Estatus = 0
		 WHERE 
		 fk_IdCredencial = @IdUsuario AND Estatus = 1;
			SET @SesionXML = '<Valido>0</Valido><Mensaje>
			LIMITE DE SESIONES
			</Mensaje><SesionInfo></SesionInfo>'
	END
		SET @SesionXML.modify('insert sql:variable("@InfoXML") as last into (/SesionInfo)[1]')
END

GO
/****** Object:  StoredProcedure [dbo].[pa_ValidarSesionB]    Script Date: 03/12/2016 05:25:05 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_ValidarSesionB]
	@IdUsuario bigint,
	@TOKEN varchar(50)
	
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @SesionXML xml;
	DECLARE @Count int = 1;
	DECLARE @InfoXML xml;
	-- Cuenta todas las sesiones activas y guarda el numero en la varuable @Count:
	SELECT @Count = COUNT(fk_IdCredencial) FROM dbo.Sesion
	WHERE fk_IdCredencial = @IdUsuario AND Estatus = 1 AND Token LIKE @TOKEN;

	--Validar si hay una o menos sesiónes (menos de dos sesiones)
	IF @Count = 1 --@Count < 1
		BEGIN
			SET @SesionXML = '<Respuesta><Valido>1</Valido><Mensaje>Hay Sesion Activa</Mensaje></Respuesta>';
		END
	ELSE -- si tiene 1 sesion cambia la sesion actual: 
		BEGIN
			SET @SesionXML = '<Respuesta><Valido>0</Valido><Mensaje>NO HAY SESION ABIERTA</Mensaje></Respuesta>'; 
		END		-- LOGIN autenticador por credencial, por token y qye este activa la sesion
	SELECT @InfoXML = (
			SELECT dbo.Sesion.*, @IdUsuario as IdUsuario From dbo.Sesion 
			WHERE fk_IdCredencial = @IdUsuario 
			AND Token = @TOKEN 
			AND Estatus = 1
			FOR XML PATH('SesionInfo'), ELEMENTS 
			--adentro del path '' va la etiqueta en la que se quiere meter info en este caso no se requiere una etiqueta en especifico
		)
	--ELSE
	--	BEGIN
	--		UPDATE dbo.Sesiones SET sesi_estatus = 0
	--		WHERE sesi_credId = @IdUsuario AND sesi_token = @TOKEN AND sesi_estatus = 1; 
	--		SET @SesionXML = '<Valido>0</Valido><Mensaje>LIMITE DE SESIONES</Mensaje>'
	--	END
	SET @SesionXML.modify('insert sql:variable("@InfoXML") as last into (/Respuesta)[1]');
	SELECT(@SesionXML);
END

GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "C"
            Begin Extent = 
               Top = 12
               Left = 76
               Bottom = 444
               Right = 415
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "P"
            Begin Extent = 
               Top = 44
               Left = 707
               Bottom = 438
               Right = 1014
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 750
         Width = 750
         Width = 750
         Width = 750
         Width = 750
         Width = 750
         Width = 750
         Width = 750
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_Cliente'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'V_Cliente'
GO
USE [master]
GO
ALTER DATABASE [PekesBD] SET  READ_WRITE 
GO
