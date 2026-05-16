# 工时地址文档导入说明

## 已完成功能

已在 mall-chat-rag 服务中实现了文档导入功能，包括：

### 1. 核心服务类 (`DocumentService`)

- 读取 resources 文件夹下的 MD 文档
- 使用 Markdown 解析器解析文档内容
- 使用 TokenTextSplitter 进行文本切分（块大小1000，重叠200）
- 调用 Embedding Model 生成文本向量
- 保存到 PostgreSQL 的 pgvector 表中

### 2. 自动导入任务 (`DocumentImportRunner`)

- 实现了 CommandLineRunner 接口
- 服务启动时自动导入 `工时地址.md` 文档
- 如导入失败会在日志中记录错误信息

### 3. REST API 接口 (`DocumentController`)

提供了两个手动触发导入的接口：

- **POST** `/api/document/import?fileName=工时地址.md`
  - 通用导入接口，通过参数指定文件名

- **POST** `/api/document/import/work-time`
  - 专门用于导入工时地址文档的快捷接口

### 4. 向量存储配置 (`VectorStoreConfig`)

- 配置了 pgvector 向量存储
- 使用 cosine 相似度计算
- 向量维度：1536
- 集合名称：chat_rag_collection
- 自动初始化数据库表结构

## 使用说明

### 方式一：服务启动时自动导入

服务启动后，会自动执行 `DocumentImportRunner`，将 `工时地址.md` 文件导入到向量库中。

### 方式二：通过 API 手动触发导入

使用 cURL 或 Postman 调用接口：

```bash
# 通用导入接口
curl -X POST "http://localhost:9998/api/document/import?fileName=工时地址.md"

# 工时地址专用接口
curl -X POST "http://localhost:9998/api/document/import/work-time"
```

## 数据库表结构

向量数据将存储在 PostgreSQL 的以下表中：

- 表名：`vector_store`（Spring AI pgvector 默认表名）
- 主要字段：
  - `id` - 文档片段唯一标识
  - `content` - 文本内容
  - `embedding` - 向量数据（1536维）
  - `metadata` - 元数据（JSON格式）

## 注意事项

1. 确保 PostgreSQL 数据库已正确配置并包含 pgvector 扩展
2. 确保 `bootstrap.yml` 中的数据库连接配置正确
3. 确保 `aliQwen-api` 配置项已设置有效的阿里云 API Key
4. 文档导入后可在 PostgreSQL 中查询 `vector_store` 表验证数据
5. 如需重新导入相同文档，建议先清空 `vector_store` 表中相关数据

## 文件清单

- `src/main/java/com/mall/chatrag/service/DocumentService.java` - 文档处理服务
- `src/main/java/com/mall/chatrag/runner/DocumentImportRunner.java` - 启动时自动导入
- `src/main/java/com/mall/chatrag/controller/DocumentController.java` - REST API 接口
- `src/main/java/com/mall/chatrag/config/VectorStoreConfig.java` - 向量存储配置（已更新）
