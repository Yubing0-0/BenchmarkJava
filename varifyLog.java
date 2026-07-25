
@Slf4j
@RestController
@RequestMapping("/manage/dataasset/column")
@Tag(name = "数据资产字段信息管理", description = "数据资产字段信息管理服务")
public class DataAssetColumnInfoController {
  @Resource private DataAssetColumnInfoService dataAssetColumnInfoService;

  /**
   * 分页查询字段信息
   *
   * @param pageNum 页码
   * @param pageSize 条数
   * @param tableName 表名
   * @return 表实体列表
   */
  @GetMapping("/query")
  @Operation(summary = "分页查询字段信息", description = "分页查询字段信息，支持按表名筛选")
  public Result<IPage<DataAssetColumnInfo>> queryColumnInfo(
      @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
      @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize,
      @Parameter(description = "表名") @RequestParam(required = false) String tableName) {
    log.info(
        "Receive paging query request, pageNum={}, pageSize={}, tableName={}",
        pageNum,
        pageSize,
        tableName);

    Page<DataAssetColumnInfo> page = new Page<>(pageNum, pageSize);
    IPage<DataAssetColumnInfo> result = dataAssetColumnInfoService.pageList(page, tableName);

    log.info("Query completed, total {} records", result.getTotal());
    return Result.success(result);
  }

  /**
   * Description:编辑字段信息
   *
   * @param req 参数
   * @return 更新结果
   * @since 2026/3/11
   */
  @PostMapping("/update")
  @Operation(summary = "编辑字段信息", description = "根据columnInfoId和tableName更新字段信息")
  @LogApi(
      operationModule = "数字化运营看板",
      operation = "编辑元数据表字段信息",
      remark = "编辑元数据表字段信息",
      isBackData = true)
  public Result<Boolean> updateColumnInfo(
      @Parameter(description = "字段信息") @RequestBody @Valid DataAssetColumnInfoUpdateReq req) {
    log.info(
        "Receive update column info request, columnInfoId={}, tableName={}",
        req.getColumnInfoId(),
        req.getTableName());

    boolean isUpdateSuccess = dataAssetColumnInfoService.updateColumnInfo(req);

    if (isUpdateSuccess) {
      log.info("Update completed successfully");
      return Result.success(true);
    } else {
      log.warn("Update failed");
      return Result.success(false);
    }
  }
}
