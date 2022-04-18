package Portal2D.src.Components;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.Body;
import java.awt.Component;
import org.jbox2d.collision.*;

import Portal2D.src.enums.BodyType;

import java.awt.Component;
import java.util.Vector; 


public class RigidBody2D extends Component{
	private Vec2 velocity = new Vec2();
	private float angularDamping = 0.8f;
	private float linearDamping = 0.9f;
	private float mass = 0;
	private BodyType bodyType = BodyType.Dynamic;
	
	private boolean fixedRotation = false;
	private boolean continuousRotation = false;
	
	private Body rawBody = null;
	
	public void update(float dt) {
		if(rawBody != null) {
			
		}
	}
	public Vec2 getVector() {
		return velocity;
	}
	public void setVector(Vec2 vel) {
		velocity = vel;
	}
	public float getAngularDamping() {
		return angularDamping;
	}
	public void setAngularDamping(float a) {
		angularDamping = a;
	}
	public float getLinearDamping() {
		return linearDamping;
	}
	public void setLinearDamping(float l) {
		linearDamping = l;
	}
	public float getMass() {
		return mass;
	}
	public void setMass(float m) {
		mass = m;
	}
	public BodyType bodyType() {
		return bodyType;
	}
	public void setBodyType(BodyType bd) {
		bodyType = bd;
	}
	public boolean getFixRot() {
		return fixedRotation;
	}
	public void setFixRot(boolean fr) {
		fixedRotation = fr;
	}
	public boolean getContRot() {
		return continuousRotation;
	}
	public void setContRot(boolean cr) {
		continuousRotation = cr;
	}
	public Body getBody() {
		return rawBody;
	}
	private void setBody(Body b) {
		rawBody = b;
	}
}

