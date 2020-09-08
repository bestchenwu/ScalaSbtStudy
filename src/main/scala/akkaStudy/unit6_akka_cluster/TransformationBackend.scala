package akkaStudy.unit6_akka_cluster

import akka.actor.{Actor, RootActorPath}
import akka.cluster.{Cluster, Member, MemberStatus}
import akka.cluster.ClusterEvent.{CurrentClusterState, MemberEvent, MemberUp, UnreachableMember}
import akka.event.Logging

class TransformationBackend extends Actor {

  val log = Logging(context.system, this)
  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp], classOf[UnreachableMember])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case MemberUp(member) => register(member) // 将刚处于Up状态的节点向集群客户端注册
    case x: UnreachableMember => log.info(s"unreach member:${x.member}")
    case state: CurrentClusterState => state.members.filter(_.status == MemberStatus.Up) foreach register // 根据节点状态向集群客户端注册
    case url: String => {
      //接受任务请求
      val result = ArticleParser(url)
      //返回结果
      sender() ! result
    }
  }

  def register(member: Member) = {
    println(s"-------register:$member,role=${member.getRoles}")
    if(member.hasRole("frontend")){
      context.actorSelection(RootActorPath(member.address) / "user" / "frontend") ! BackendRegistration
    }


  }
}
