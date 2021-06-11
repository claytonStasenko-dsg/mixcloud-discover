package com.cstasenko.mixclouddiscover

import com.cstasenko.mixclouddiscover.model.MixcloudShow
import com.cstasenko.mixclouddiscover.model.User
import com.google.gson.Gson
import java.io.InputStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestUtils {
    companion object {
        inline fun <reified T : Any> getLocalJsonFile(fileName: String): T {
            val input: InputStream =
                ClassLoader.getSystemResourceAsStream(fileName)
            val inputAsString = input.bufferedReader().use { it.readText() }
            return Gson().fromJson(inputAsString, T::class.java)
        }

        fun buildMixcloudUserShowsFlow(): Flow<List<MixcloudShow>> {
            return flow {
                emit(
                    listOf(
                        MixcloudShow(
                            key = "/thelotradio/three6sashia-the-lot-radio-06-09-2021/",
                            name = "Three6sashia @ The Lot Radio 06-09-2021 .",
                            link = "https://www.mixcloud.com/thelotradio/three6sashia-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/prince-language-the-lot-radio-06-09-2021/",
                            name = "Prince Language @ The Lot Radio 06-09-2021",
                            link = "https://www.mixcloud.com/thelotradio/prince-language-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/3/3/1/9/d0d7-d0dd-4751-b8a2-251af895878a",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/nicola-cruz-the-lot-radio-06-09-2021/",
                            name = "Nicola Cruz @ The Lot Radio 06-09-2021",
                            link = "https://www.mixcloud.com/thelotradio/nicola-cruz-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/a/7/b/2/c453-8b7e-4c72-905a-7864d3e89e3d",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/hope-porn-with-timo-ellis-the-lot-radio-06-09-2021/",
                            name = "HOPE PORN with TIMO ELLIS @ The Lot Radio 06-09-2021",
                            link = "https://www.mixcloud.com/thelotradio/hope-porn-with-timo-ellis-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/f/3/c/3/1df4-b359-46cb-b097-aa24c79f615a",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/georgia-the-lot-radio-06-09-2021/",
                            name = "Georgia @ The Lot Radio 06-09-2021",
                            link = "https://www.mixcloud.com/thelotradio/georgia-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/d/7/a/0/c687-689c-49bd-a387-ff450829da11",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/forty-deuce-the-lot-radio-06-09-2021/",
                            name = "Forty Deuce @ The Lot Radio 06-09-2021",
                            link = "https://www.mixcloud.com/thelotradio/forty-deuce-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/8/5/3/f/e8bb-1015-4eff-9667-d18e92e1f428",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/analog-soul-the-lot-radio-06-09-2021/",
                            name = "Analog Soul @ The Lot Radio 06-09-2021",
                            link = "https://www.mixcloud.com/thelotradio/analog-soul-the-lot-radio-06-09-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/4/a/0/7/979d-d15e-4203-ac3b-cca8f0975ac3",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/pique-nique-am-the-lot-radio-06-08-2021/",
                            name = "Pique-nique A.M. @ The Lot Radio 06-08-2021",
                            link = "https://www.mixcloud.com/thelotradio/pique-nique-am-the-lot-radio-06-08-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/1/2/e/8/a101-1291-4fe7-986a-9e5c78fc5ef3",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/commend-anywhere-wolivia-bradley-skill-the-lot-radio-06-08-2021/",
                            name = "Commend Anywhere w/ Olivia Bradley-Skill @ The Lot Radio 06-08-2021",
                            link = "https://www.mixcloud.com/thelotradio/commend-anywhere-wolivia-bradley-skill-the-lot-radio-06-08-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/0/8/1/5/950f-31a7-465c-8b6c-99c45fec7f63",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        ),
                        MixcloudShow(
                            key = "/thelotradio/cassette-pm-with-fede-pirozzi-the-lot-radio-06-08-2021/",
                            name = "CASSETTE PM with Fede Pirozzi @ The Lot Radio 06-08-2021",
                            link = "https://www.mixcloud.com/thelotradio/cassette-pm-with-fede-pirozzi-the-lot-radio-06-08-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/f/7/e/0/185c-eae9-4da7-a1ab-e65ee376ed0e",
                            User(
                                userName = "The Lot Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/0/5/4/0/bc58-b019-4df2-9f69-19e0746f6aca.png"
                            )
                        )
                    )
                )
            }
        }

        fun buildMixcloudShowDomainObjectsFlow(): Flow<List<MixcloudShow>> {
            return flow {
                emit(
                    listOf(
                        MixcloudShow(
                            key = "/NTSRadio/theo-parrish-presents-eargoggles-6-hour-mix-nts-10-22nd-april-2021/",
                            name = "Theo Parrish Presents: eargoggles (6 Hour Mix) - NTS 10 - 22nd April 2021",
                            link = "https://www.mixcloud.com/NTSRadio/theo-parrish-presents-eargoggles-6-hour-mix-nts-10-22nd-april-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/c/c/2/3/d4d1-248b-4294-b71f-4285d9625a9f",
                            User(
                                userName = "NTS Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg"
                            )
                        ),
                        MixcloudShow(
                            key = "/NTSRadio/jamie-xx-2nd-april-2021/",
                            name = "Jamie XX - 2nd April 2021",
                            link = "https://www.mixcloud.com/NTSRadio/jamie-xx-2nd-april-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/6/d/b/e/fdae-19eb-40c3-9d09-acf40a85d17b",
                            User(
                                userName = "NTS Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg"
                            )
                        ),
                        MixcloudShow(
                            key = "/NTSRadio/black-classical-history-of-spiritual-jazz-part-1/",
                            name = "Black Classical - History of Spiritual Jazz Part 1",
                            link = "https://www.mixcloud.com/NTSRadio/black-classical-history-of-spiritual-jazz-part-1/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/3/6/4/4/8f0f-6d80-40cb-b405-7b62ca77bfdd",
                            User(
                                userName = "NTS Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg"
                            )
                        ),
                        MixcloudShow(
                            key = "/NTSRadio/dj-nobu-26th-february-2021/",
                            name = "DJ Nobu - 26th February 2021",
                            link = "https://www.mixcloud.com/NTSRadio/dj-nobu-26th-february-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/6/6/9/c/b62d-842b-449f-b1c5-edc2f452313f",
                            User(
                                userName = "NTS Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg"
                            )
                        ),
                        MixcloudShow(
                            key = "/NTSRadio/laurel-halo-11th-january-2021/",
                            name = "Awe w/ Laurel Halo  - 11th January 2021",
                            link = "https://www.mixcloud.com/NTSRadio/laurel-halo-11th-january-2021/",
                            imageUrl = "https://thumbnailer.mixcloud.com/unsafe/600x600/extaudio/1/8/7/a/d146-0021-401d-86d0-31c2a9591f40",
                            User(
                                userName = "NTS Radio",
                                userAvatarUrl = "https://thumbnailer.mixcloud.com/unsafe/80x80/profile/6/c/6/2/4cae-6e61-411c-adb6-9d9cd3e9112c.jpg"
                            )
                        )
                    )
                )
            }
        }
    }
}
