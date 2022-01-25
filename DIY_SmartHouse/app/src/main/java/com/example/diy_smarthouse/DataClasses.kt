package com.example.diy_smarthouse

data class Room(var room: String ?= null, var type: String ?= null)

data class Device(var device: String ?=null, var type: String ?= null, var room: String ?= null)

// Device type classes
data class Light(var intensity: Int ?= null, var color: Int ?= null, var id: String ?= null)

data class AC(var temperature: Int ?= null, var fan: Int ?= null, var id: String ?= null)

data class Thermostat(var temperature: Int ?= null, var humidity: Int ?= null)

data class Sound(var volume: Int ?= null)

data class Fan(var speed: String ?= null)

data class Location(var latitude: Double, var longitude: Double)