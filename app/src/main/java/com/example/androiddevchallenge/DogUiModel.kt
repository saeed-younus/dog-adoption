package com.example.androiddevchallenge

data class DogUiModel(
    val name: String,
    val breed: DogBreed,
    val detail: String,
)

enum class DogBreed(val value: String) {
    GermanShepherd("German Shepherd"),
    Husky("Husky"),
    BullDog("Bull Dog"),
    Labrador("Labrador"),
    Poodle("Poodle"),
}

fun getDogsList(): List<DogUiModel> {
    val names = listOf("Rockie", "Natty", "Crappy", "Kiddy", "Candy", "Greamy")
    val breeds = listOf(
        DogBreed.GermanShepherd,
        DogBreed.Husky,
        DogBreed.BullDog,
        DogBreed.Labrador,
        DogBreed.Poodle,
    )
    val list: MutableList<DogUiModel> = mutableListOf()
    for (i in 1..30) {
        list.add(
            DogUiModel(
                name = names.random(),
                breed = breeds.random(),
                detail = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque maximus, augue sed egestas vulputate, lectus nisl dictum erat, a faucibus arcu eros in nunc. Proin eleifend gravida luctus. Fusce molestie ipsum quis orci facilisis suscipit. Sed pretium dictum accumsan. Donec lacinia a diam vitae ultricies. Nullam ac dapibus augue. Pellentesque blandit mi vitae turpis egestas, vitae tincidunt mauris porttitor. Ut ut molestie augue. Ut feugiat enim at elit tempor tempor. Pellentesque dignissim ornare felis, eu convallis mauris tincidunt eget.\n" +
                        "\n" +
                        "Sed eu nisi et enim luctus elementum at et nunc. Vestibulum bibendum auctor dui eu pulvinar. Donec ullamcorper placerat mauris et dignissim. Duis laoreet tortor in risus rutrum sodales. Integer lorem est, tempor quis quam sit amet, pretium mattis lacus. Nam ac sagittis nulla. Cras metus elit, aliquet eu pretium eu, mattis ac eros. Nulla eu venenatis ex. Aliquam pharetra neque ac urna dapibus commodo. Vivamus egestas mollis magna, blandit interdum risus ullamcorper ut. Nullam iaculis, quam eu euismod consequat, lectus turpis dictum ligula, quis laoreet nibh arcu et mauris. Nunc non vehicula dolor. Morbi turpis turpis, faucibus a lacinia eget, porta nec lorem. Fusce at finibus dui. Sed malesuada nisi sed posuere euismod. Curabitur auctor eleifend magna a scelerisque.\n" +
                        "\n" +
                        "Nunc at luctus erat, a porta ligula. Vestibulum ornare tristique ornare. Pellentesque varius finibus neque. Praesent eu diam non nisl congue condimentum quis sed urna. Phasellus eu augue eros. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin mattis est non turpis malesuada bibendum. Pellentesque vel feugiat ligula, non feugiat augue. Donec eleifend id massa ut facilisis. Proin bibendum quis nisl id tempus. Cras erat libero, consequat vel pellentesque eu, efficitur eu magna. Maecenas molestie tempor vestibulum. Pellentesque nec iaculis arcu.\n" +
                        "\n" +
                        "Mauris erat tortor, vestibulum ut tellus et, tempor sagittis nibh. Suspendisse elementum fermentum tortor at venenatis. In eget nisl enim. Suspendisse lectus justo, sollicitudin eu tellus ac, ultrices faucibus velit. Integer aliquet sapien quis sollicitudin euismod. Aliquam varius at velit vel mattis. Nam congue, sem at lacinia consectetur, ipsum lacus rhoncus justo, id rutrum est mi sit amet massa. Nam rutrum bibendum ullamcorper. Vestibulum gravida tellus eget magna pulvinar, sit amet pellentesque lorem tincidunt. Maecenas facilisis commodo dui, ac sodales diam gravida sit amet. Duis turpis dolor, elementum sollicitudin consequat et, placerat et arcu. Quisque tempus non mauris id rutrum. Praesent mollis scelerisque eros a sollicitudin. Mauris volutpat ultricies ligula at semper. Cras in ligula orci.",
            )
        )
    }
    return list
}
