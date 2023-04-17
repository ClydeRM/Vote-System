const typicalSQLInjectionRegEx = /(%27)|(\')|(--)|(%23)|(#)/i;
const simpleCSSAttack = /((\%3C)|<)((\%2F)|\/)*[a-z0-9\%]+((\%3E)|>)/i;
const imgSrcCSSattackRegEx = /((\%3C)|<)((\%69)|i|(\%49))((\%6D)|m|(\%4D))((\%67)|g|(\%47))[^\n]+((\%3E)|>)/i;

export function checkValue(str) {
  try {
    if (!str) {
      throw new Error("Value is empty.");
    }
    if (typeof str !== "string") {
      throw new Error("Value is not a string.");
    }

    // Remove SQL injection patterns
    str = str.replace(
      typicalSQLInjectionRegEx,
      ""
    );

    // Remove CSS attack patterns
    str = str.replace(simpleCSSAttack, "");

    // Remove img src CSS attack patterns
    str = str.replace(imgSrcCSSattackRegEx, "");

    return str;
  } catch (e) {
    console.error(e);
  }
}
