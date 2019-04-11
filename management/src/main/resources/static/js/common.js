/*
 * 手机号正则表达式
 */
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}
/*
 * 邮箱正则表达式
 */
function isEmailNo(email) {
    var pattern = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
    return pattern.test(email);
}
/*
 * QQ正则表达式
 */
function isQqNo(qq) {
    var pattern = /[1-9][0-9]{4,14}/;
    return pattern.test(qq);
}

/*
 *验证账号
 */
function checkUsername() {
    if ($.trim($('#username').val()).length == 0) {
        $('#usernameError').html('账号没有输入');
        $('#username').focus();
        return false;
    }else {
        return true;
    }
}

/*
 * 验证手机号
 */
function checkPhone() {
    if ($.trim($('#telephone').val()).length == 0) {
        $('#telephoneError').html('手机号码没有输入');
        $('#telephone').focus();
        return false;
    } else {
        $('#telephoneError').html('');
        if (isPhoneNo($('#telephone').val()) == false) {
            $('#telephoneError').html('手机号码不正确');
            $('#telephone').focus();
            return false;
        } else {
            return true;
        }
    }
}
/*
 * 验证密码
 */
function checkPassword() {
    if ($.trim($('#password').val()).length == 0) {
        $('#passwordError').html('密码没有输入');
        $('#password').focus();
        return false;
    } else {
        $('#passwordError').html('');
        var rePass = $.trim($('#rePassword').val());
        var pass = $.trim($('#password').val());
        if (rePass.length != 0) {
            if (rePass != pass) {
                $('#rePasswordError').html('两次密码不一致');
                return false;
            } else {
                $('#rePasswordError').html('');
                return true;
            }
        }
    }
}
/*
 * 重复密码
 */
function checkrePassword() {
    var rePass = $.trim($('#rePassword').val());
    if (rePass.length == 0) {
        $('#rePasswordError').html('密码没有输入');
        $('#rePassword').focus();
        return false;
    } else {
        $('#rePasswordError').html('');
        var pass = $.trim($('#password').val());
        if (pass.length == 0) {
            $('#rePassword').val('');
            $('#passwordError').html('密码没有输入');
            $('#password').focus();
            return false;
        } else if (rePass != pass) {
            $('#rePasswordError').html('两次密码不一致');
            return false;
        } else {
            $('#rePasswordError').html('');
            return true;
        }
    }
}
/*
 * 验证联系人
 */
function checkName() {
    if ($.trim($('#name').val()).length == 0) {
        $('#nameError').html('联系人没有输入');
        $('#name').focus();
        return false;
    } else {
        $('#nameError').html('');
        return true;
    }
}
/*
 * 验证邮箱
 */
function checkEmail() {
    if ($.trim($('#email').val()).length == 0) {
        $('#emailError').html('邮箱没有输入');
        $('#email').focus();
        return false;
    } else {
        $('#emailError').html('');
        if (isEmailNo($('#email').val()) == false) {
            $('#emailError').html('邮箱格式不正确');
            $('#email').focus();
            return false;
        } else {
            $('#emailError').html('');
            return true;
        }
    }
}
/*
 * 验证QQ
 */
function checkQQ() {
    if ($.trim($('#qq').val()).length == 0) {
        $('#qqError').html('QQ没有输入');
        $('#qq').focus();
        return false;
    } else {
        $('#qqError').html('');
        if (isQqNo($('#qq').val()) == false) {
            $('#qqError').html('QQ格式不正确');
            $('#qq').focus();
            return false;
        } else {
            $('#qqError').html('');
            return true;
        }
    }
}
/*
 * 注册表单提交时验证
 */
function checkRegister() {
    if (checkPhone() == false) {
        return false;
    }
    if (checkPassword() == false) {
        return false;
    }
    if (checkrePassword() == false) {
        return false;
    }
    if (checkName() == false) {
        return false;
    }
    if (checkEmail() == false) {
        return false;
    }
    if (checkQQ() == false) {
        return false;
    }
}
