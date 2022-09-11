-keep class retrofit.** { *; }
-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}
-keep public class com.android.installreferrer.** { *; }
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class fivesol.networklibrary.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class fivesol.networklibrary.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class fivesol.networklibrary.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**
-keepattributes *Annotation*
