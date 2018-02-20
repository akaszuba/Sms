package com.artur.sms.Utility

/**
 * Created by Artur on 2/15/2018.
 */
fun String.formatPhone(phoneNumberLength: Int = 9, defaultCountryCode: String = "48"): String{
    return  Regex("[^\\d]").replace(this,"")
            .convert { number->"$+{number.getPrefix(phoneNumberLength,defaultCountryCode)}${number.getSuffix(phoneNumberLength)}" }
}

fun String.getSuffix(length : Int): String{
    return if (this.length <= length ) this
    else this.substring(this.length - length)
}

fun String.getPrefix(phoneNumberLength:Int,defaultCountryCode:String):String{
    return if (phoneNumberLength>= this.length) defaultCountryCode else this.substring(0, this.length - phoneNumberLength).getSuffix(2)
}

fun <Tin, Tout> Tin.convert(func:(Tin)->Tout):Tout{
    return func.invoke(this)
}