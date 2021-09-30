### **Endpoints** `/api-stock`
#### `Cargar stock de productos`
##### @PUT -> /items/{itemId}
| url parameter | type | required | 
| :---: | :---: | :---: |
| itemId | String | true |

> Body

```json
{
    "deposit":"AR03",
    "quantity":10,
    "location": "AA-00-10-DE"
}
```
##### Response -> StockDTO
| parameter     |   type | 
| :---:         |   :---: |
| deposit       |   String |
| location       | String |
| quantity    | int | 

```json
{
	"deposit": "AR03",
	"quantity": 10,
	"location": "AA-00-10-DE"
}
```
---
#### `Retirar productos de stock`
##### @PUT -> /items/{itemId}/consume
| url parameter | type | required | 
| :---: | :---: | :---: |
| itemId | String | true |

> Body

```json
{
	"deposit": "AR03",
	"quantity": 10,
	"location": "AA-00-10-DE"
}
```
##### Response -> [StockDTO](#response-stockdto)

---

#### `Consultar productos segun ubicación y depósito`
##### @GET -> /items
| url parameter | type | required | 
| :---: | :---: | :---: |
| location | String | true |
| deposit | String | true |

##### Response -> ItemWrapperDTO
| parameter     |   type | 
| :---:         |   :---: |
| location       |   String |
| deposit       | String |
| total     | int | 
| items     | ItemDTO[] |

##### ItemDTO
| parameter     |   type | 
| :---:         |   :---: |
| itemId | String |
| quantity | int |
| location | String |

```json
{
	"location": "AA-00-10-DE",
	"deposit": "AR03",
	"total": 10,
	"items": [{
		"itemId": "MLA813727183",
		"quantity": 10
	}]
}
```
---

#### `Busqueda de productos`
##### @GET -> /items/search
| url parameter | type | required | 
| :---: | :---: | :---: |
| deposit | String | true |
| itemId | String | true |
| page | int | false |
| size | int | false |

##### Response -> PaginationWrapperDTO
| parameter     |   type | 
| :---:         |   :---: |
| totalItems       |   long |
| totalPages       | long |
| currentPage     | long | 
| items     | [ItemDTO[]](#response-itemdto) |

```json
{
	"totalItems": 2,
	"totalPages": 1,
	"currentPage": 0,
	"items": [{
		"itemId": "MLA813727183",
		"quantity": 30,
		"location": "AA-00-00-DE"
	}, {
		"itemId": "MLA813727183",
		"quantity": 0,
		"location": "AA-00-10-DE"
	}]
}
```