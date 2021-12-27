package com.app.postmaker.eventbus

class Events {

    // Event used to send message from color data notify.
    class Notify(private var string: String, private var position: Int) {
        fun getString(): String {
            return string
        }

        fun setString(string: String) {
            this.string = string
        }

        fun getPosition(): Int {
            return position
        }

        fun setString(position: Int) {
            this.position = position
        }
    }

}