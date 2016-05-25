package de.eichstaedt.domain.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Authentication {

  private String loginName;

  private String password;

  protected Authentication() {
    super();
  }

  private Authentication(String loginName, String password) {
    this.loginName = loginName;
    this.password = password;
  }

  public static Authentication build(String loginName, String password) {

    return new Authentication(loginName, password);
  }

  public String getLoginName() {
    return loginName;
  }

  public String getPassword() {
    return password;
  }
}
