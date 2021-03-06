USE [master]
GO
/****** Object:  Database [CarRental]    Script Date: 3/4/2021 7:49:23 PM ******/
CREATE DATABASE [CarRental]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CarRental', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarRental.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CarRental_log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarRental_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CarRental] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CarRental].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CarRental] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CarRental] SET ARITHABORT OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CarRental] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CarRental] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CarRental] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CarRental] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CarRental] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CarRental] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CarRental] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CarRental] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CarRental] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CarRental] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CarRental] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CarRental] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CarRental] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CarRental] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CarRental] SET  MULTI_USER 
GO
ALTER DATABASE [CarRental] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CarRental] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CarRental] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CarRental] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [CarRental] SET DELAYED_DURABILITY = DISABLED 
GO
USE [CarRental]
GO
/****** Object:  Table [dbo].[tblCar]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCar](
	[id] [nchar](10) NOT NULL,
	[name] [nvarchar](100) NULL,
	[colorID] [int] NOT NULL,
	[yearID] [int] NOT NULL,
	[categoryID] [int] NOT NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[image] [nchar](1000) NULL,
	[userUpdate] [nchar](100) NOT NULL,
	[status] [bit] NULL,
	[date] [date] NULL,
 CONSTRAINT [PK_Car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[id] [int] NOT NULL,
	[categoryName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblColor]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblColor](
	[id] [int] NOT NULL,
	[colorName] [nchar](10) NULL,
 CONSTRAINT [PK_tblColor] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[code] [nvarchar](50) NOT NULL,
	[startDate] [date] NOT NULL,
	[endDate] [date] NOT NULL,
	[value] [float] NOT NULL,
 CONSTRAINT [PK_tblDiscount] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblDiscountOrderDetail]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscountOrderDetail](
	[orderID] [int] NOT NULL,
	[discountID] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblDiscountOrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC,
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userID] [nchar](100) NOT NULL,
	[dateOrder] [date] NULL,
	[totalPrice] [float] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderID] [int] NOT NULL,
	[carID] [nchar](10) NOT NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
	[rentalDate] [date] NULL,
	[returnDate] [date] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC,
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUser](
	[userID] [nchar](100) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[isAdmin] [bit] NOT NULL,
	[status] [bit] NOT NULL,
	[phone] [varchar](10) NULL,
	[address] [nvarchar](50) NULL,
	[createDate] [date] NOT NULL,
	[code] [nchar](6) NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblYear]    Script Date: 3/4/2021 7:49:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblYear](
	[id] [int] NOT NULL,
	[year] [int] NOT NULL,
 CONSTRAINT [PK_tblYear] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'1         ', N'Car czech', 4, 1, 1, 32, 0, N'https://pixy.org/src/39/thumbs350/397552.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ', N'admin1                                                                                              ', 0, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'10        ', N'Mazda Mazda3', 6, 1, 3, 31, 43, N'https://cars.usnews.com/static/images/Auto/izmo/i159614324/2021_mazda_mazda_3_angularfront.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'11        ', N'Honda Civic', 1, 2, 3, 30, 0, N'https://cars.usnews.com/static/images/Auto/izmo/i159614281/2021_honda_civic_sedan_angularfront.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ', N'admin1                                                                                              ', 0, CAST(N'2021-02-27' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'12        ', N'Kia Forte', 6, 2, 3, 29, 107, N'https://cars.usnews.com/static/images/Auto/izmo/i159614227/2021_kia_forte_angularfront.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'13        ', N'JEEP WRANGLER', 1, 1, 2, 33, 100, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/kia-rio-4d-weiss-2018.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'14        ', N'JEEP GRAND CHEROKEE', 1, 1, 3, 15, 72, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/jeep-grand-cherokee-5d-weiss-2018.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'15        ', N'BMW X1', 2, 1, 1, 50, 20, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/bmw-x1-5d-weiss-2019.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'16        ', N'TOYOTA TACOMA', 1, 1, 1, 45, 20, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/toyota-tacoma-dcab-grau-2017.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'17        ', N'TOYOTA CAMRY', 2, 1, 2, 55, 20, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/mb-a-klasse-5d-weiss-2018.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'18        ', N'CHEVROLET SUBURBAN', 1, 1, 1, 60, 20, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/chevrolet-suburban-5d-schwarz-2016.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'19        ', N'VOLVO XC60', 1, 1, 1, 70, 20, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/volvo-xc60-schwarz-2020.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'2         ', N'Mercedes-Benz SLC', 4, 3, 2, 28, 20, N'https://images.hertz.com/misc/convertible-mercedes-SLC-desktop.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'20        ', N'BMW 7 SERIES', 1, 1, 1, 80, 67, N'https://www.sixt.com/fileadmin/files/global/user_upload/fleet/png/350x200/mb-sl-cabrio-2d-grau-2012.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'3         ', N'Chevrolet Camaro ', 3, 3, 3, 27, 20, N'https://images.hertz.com/misc/convertible-chevrolet-camaro-desktop-blue.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'4         ', N'Porsche Boxster ', 2, 2, 3, 26, 20, N'https://images.hertz.com/misc/convertible-porsche-boxster-desktop.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ', N'admin1                                                                                              ', 1, CAST(N'2021-02-26' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'5         ', N'BLACK LUXURY AUDI CAR', 1, 2, 1, 40, 18, N'https://pixy.org/src/39/thumbs350/394219.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ', N'admin1                                                                                              ', 1, CAST(N'2021-03-01' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'6         ', N'LUXURY SPORT CAR', 5, 3, 1, 25, 73, N'https://pixy.org/src/486/thumbs350/4868292.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ', N'admin1                                                                                              ', 1, CAST(N'2021-03-01' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'7         ', N'NISSAN SENTRA', 4, 3, 2, 24, 20, N'https://sx-content-labs.sixt.io/Media/2fleet-350x200/nissan-sentra-4d-grau-2016.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ', N'admin1                                                                                              ', 1, CAST(N'2021-03-01' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'8         ', N'CHEVROLET CRUZE', 1, 1, 2, 23, 20, N'https://sx-content-labs.sixt.io/Media/2fleet-350x200/chevrolet-cruze-4d-silber-2018.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ', N'admin1                                                                                              ', 1, CAST(N'2021-03-01' AS Date))
INSERT [dbo].[tblCar] ([id], [name], [colorID], [yearID], [categoryID], [price], [quantity], [image], [userUpdate], [status], [date]) VALUES (N'9         ', N'HYUNDAI ELANTRA', 1, 1, 2, 32, 20, N'https://sx-content-labs.sixt.io/Media/2fleet-350x200/hyundai-elantra-4d-schwarz-2017.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ', N'admin1                                                                                              ', 1, CAST(N'2021-03-01' AS Date))
INSERT [dbo].[tblCategory] ([id], [categoryName]) VALUES (1, N'luxury')
INSERT [dbo].[tblCategory] ([id], [categoryName]) VALUES (2, N'intermediate')
INSERT [dbo].[tblCategory] ([id], [categoryName]) VALUES (3, N'compact')
INSERT [dbo].[tblColor] ([id], [colorName]) VALUES (1, N'black     ')
INSERT [dbo].[tblColor] ([id], [colorName]) VALUES (2, N'white     ')
INSERT [dbo].[tblColor] ([id], [colorName]) VALUES (3, N'blue      ')
INSERT [dbo].[tblColor] ([id], [colorName]) VALUES (4, N'silver    ')
INSERT [dbo].[tblColor] ([id], [colorName]) VALUES (5, N'yellow    ')
INSERT [dbo].[tblColor] ([id], [colorName]) VALUES (6, N'red       ')
INSERT [dbo].[tblDiscount] ([code], [startDate], [endDate], [value]) VALUES (N'ok', CAST(N'2021-02-21' AS Date), CAST(N'2021-03-20' AS Date), 5)
INSERT [dbo].[tblDiscount] ([code], [startDate], [endDate], [value]) VALUES (N'okk', CAST(N'2021-02-22' AS Date), CAST(N'2021-02-23' AS Date), 6)
INSERT [dbo].[tblDiscount] ([code], [startDate], [endDate], [value]) VALUES (N'okkk', CAST(N'2021-02-22' AS Date), CAST(N'2021-03-20' AS Date), 7)
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [isAdmin], [status], [phone], [address], [createDate], [code]) VALUES (N'admin1                                                                                              ', N'1234aA', N'a', 1, 1, NULL, NULL, CAST(N'2021-01-27' AS Date), NULL)
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [isAdmin], [status], [phone], [address], [createDate], [code]) VALUES (N'dangcongminh240420@gmail.com                                                                        ', N'123456', N'Dang', 0, 1, N'0843043382', N'52 D2A, phước long B, quận 9,HCM', CAST(N'2021-02-25' AS Date), N'CZ05Hs')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [isAdmin], [status], [phone], [address], [createDate], [code]) VALUES (N'dangminh200320@gmail.com                                                                            ', N'123456', N'Dang', 0, 1, N'1 569 8506', N'200Hoang Văn Thụ TP Quảng Ngãi', CAST(N'2021-03-04' AS Date), N'z7DdWg')
INSERT [dbo].[tblYear] ([id], [year]) VALUES (1, 2020)
INSERT [dbo].[tblYear] ([id], [year]) VALUES (2, 2021)
INSERT [dbo].[tblYear] ([id], [year]) VALUES (3, 2019)
INSERT [dbo].[tblYear] ([id], [year]) VALUES (4, 2018)
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD  CONSTRAINT [FK_tblCar_tblCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([id])
GO
ALTER TABLE [dbo].[tblCar] CHECK CONSTRAINT [FK_tblCar_tblCategory]
GO
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD  CONSTRAINT [FK_tblCar_tblColor] FOREIGN KEY([colorID])
REFERENCES [dbo].[tblColor] ([id])
GO
ALTER TABLE [dbo].[tblCar] CHECK CONSTRAINT [FK_tblCar_tblColor]
GO
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD  CONSTRAINT [FK_tblCar_tblUser] FOREIGN KEY([userUpdate])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblCar] CHECK CONSTRAINT [FK_tblCar_tblUser]
GO
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD  CONSTRAINT [FK_tblCar_tblYear] FOREIGN KEY([yearID])
REFERENCES [dbo].[tblYear] ([id])
GO
ALTER TABLE [dbo].[tblCar] CHECK CONSTRAINT [FK_tblCar_tblYear]
GO
ALTER TABLE [dbo].[tblDiscountOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscountOrderDetail_tblDiscount] FOREIGN KEY([discountID])
REFERENCES [dbo].[tblDiscount] ([code])
GO
ALTER TABLE [dbo].[tblDiscountOrderDetail] CHECK CONSTRAINT [FK_tblDiscountOrderDetail_tblDiscount]
GO
ALTER TABLE [dbo].[tblDiscountOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscountOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([id])
GO
ALTER TABLE [dbo].[tblDiscountOrderDetail] CHECK CONSTRAINT [FK_tblDiscountOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblCar] FOREIGN KEY([carID])
REFERENCES [dbo].[tblCar] ([id])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblCar]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([id])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
USE [master]
GO
ALTER DATABASE [CarRental] SET  READ_WRITE 
GO
