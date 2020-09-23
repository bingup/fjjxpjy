package com.fjjxpjy.pojo;



public class Menu  extends BasePojo {

  /***
   * 主键id
   */
  private Integer id;
  /***
   *父类id
   */
  private Integer parentId;
  /***
   *名称
   */
  private String name;
  /***
   *链接
   */
  private String url;
  /***
   *类型，1是一级菜单，2是二级菜单，3是三级菜单
   */
  private String type;
  /***
   *排序
   */
  private Integer sort;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }
}
