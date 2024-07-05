package dev.mary.infrastructure.controllers

import dev.h4kt.ktorDocs.dsl.get
import dev.h4kt.ktorDocs.types.parameters.RouteParameters
import dev.mary.infrastructure.controllers.responses.Vacancies
import dev.mary.infrastructure.services.headHunter.HeadHunterService
import dev.mary.plugins.json
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SortOrder
import org.koin.ktor.ext.inject

fun Routing.configureTestRouting() = route("/vacancies") {

    configurePublicRoutes()

//    authenticate("template") {
//        configureAuthenticatedRoutes()
//    }

}

private fun Route.configurePublicRoutes() {

    val headHunterService by inject<HeadHunterService>()

    class GetParams : RouteParameters() {

        val offset by query.long {
            name = "offset"
        }

        val limit by query.int {
            name = "limit"
        }

        val text by query.string {
            name = "query"
        }

        val area by query.string {
            name = "area"
        }

        val salary by query.int {
            name = "salary"
        }

        val orderBy by query.string {
            name = "order_by"
        }

    }

//    get(::GetParams) {
//
//        description = "Get list of all test objects"
//
//        responses {
//            HttpStatusCode.OK returns typeInfo<PageResponse<Vacancy>>()
//        }
//
//        handle {
//            call.respond(result)
//        }
//
//    }

    get(::GetParams) {

        description = "Get head hunter"

        handle {

            val client = HttpClient(CIO) {

                defaultRequest {
                    url("https://api.hh.ru")
                }

                install(ContentNegotiation.key.name) {
                    json
                }

            }

            val query = call.request.queryParameters["query"] ?: ""
            val salary = call.request.queryParameters["salary"] ?: 0
            val area = call.request.queryParameters["area"] ?: "Москва"
            val orderBy = call.request.queryParameters["order_by"] ?: SortOrder.ASC
//
//            val response: HttpResponse = client.get("vacancies") {
//                parameter("text", text)
//                parameter("salary", salary)
//                parameter("area", area)
//                parameter("order_by", orderBy)
//                parameter("per_page", perPage)
//                parameter("page", page)
//            }

            val parsingIsNeed = headHunterService.getParsingState()
            if (parsingIsNeed) {

                headHunterService.newParse()

                parse(
                    client = client,
                    page = 0,
                    headHunterService = headHunterService
                )

                call.respond(
                    headHunterService.getVacancies(
                        offset = parameters.offset,
                        limit = parameters.limit,
                        query = parameters.text,
                        area = parameters.area,
                        salary = parameters.salary,
                        orderBy = parameters.orderBy
                    )
                )

            } else {

                call.respond(
                    headHunterService.getVacancies(
                        offset = parameters.offset,
                        limit = parameters.limit,
                        query = parameters.text,
                        area = parameters.area,
                        salary = parameters.salary,
                        orderBy = parameters.orderBy
                    )
                )

            }

        }

    }

}

private suspend fun parse(
    client: HttpClient,
    page: Int,
    headHunterService: HeadHunterService
) {

    val vacancies = json.decodeFromString<Vacancies>(
        client.get("vacancies?text=&per_page=100&page=$page").bodyAsText()
    )

    headHunterService.saveVacancies(vacancies.items)

    Thread.sleep(1000)

    if (page < vacancies.pages - 1) {
        parse(client, page + 1, headHunterService)
    }

}

//private fun Route.configureAuthenticatedRoutes() {
//
//    val testRepository by inject<TestRepository>()
//
//    post("new") {
//
//        description = "Create new test object"
//        requestBody = typeInfo<Test>()
//
//        handle {
//
//            val body = call.receive<Test>()
//
//            val result = suspendedTransaction {
//                testRepository.create(body)
//            }
//
//            call.respond(result)
//        }
//
//    }
//
//}
